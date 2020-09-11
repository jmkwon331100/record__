package com.example.myrecord


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val SEOUL = LatLng(37.56, 126.97)
        val markerOptions = MarkerOptions()
        markerOptions.position(SEOUL)
        markerOptions.title("서울")
        markerOptions.snippet("한국의 수도")
        mMap!!.addMarker(markerOptions)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(SEOUL))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(10f))
    }


    // 하단부터 맵뷰의 라이프사이클 함수 호출을 위한 코드들
    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }

    // 서울 열린 데이터 광장에서 발급받은 API 키를 입력
    val API_KEY = "5a6371795764616d33335253476341"
    // 앱이 비활성화될때 백그라운드 작업도 취소하기 위한 변수 선언
    var task: ToiletReadTask? = null
    // 서울시 서비스센터 정보 집합을 저장할 Array 변수. 검색을 위해 저장
    var toilets = JSONArray()
    // 서비스센터 이미지로 사용할 Bitmap
    val bitmap by lazy {
        val drawable = resources.getDrawable(R.drawable.service_sign) as BitmapDrawable
        Bitmap.createScaledBitmap(drawable.bitmap, 64, 64, false)
    }

    // JSONArray 를 병합하기 위해 확장함수 사용
    fun JSONArray.merge(anotherArray: JSONArray) {
        for (i in 0 until anotherArray.length()) {
            this.put(anotherArray.get(i))
        }
    }

    // 서비스센터 정보를 읽어와 JSONObject 로 반환하는 함수
    fun readData(startIndex: Int, lastIndex: Int): JSONObject {
        val url =
            URL("http://openAPI.seoul.go.kr:8088" + "/${API_KEY}/json/SearchPublicToiletPOIService/${startIndex}/${lastIndex}")
        val connection = url.openConnection()
        val data = connection.getInputStream().readBytes().toString(charset("UTF-8"))
        return JSONObject(data)
    }

    // 서비스센터 데이터를 읽어오는 AsyncTask
    inner class ToiletReadTask : AsyncTask<Void, JSONArray, String>() {
        // 데이터를 읽기 전에 기존 데이터 초기화
        override fun onPreExecute() {
            // 구글맵 마커 초기화
            mMap?.clear()
            // 서비스센터 정보 초기화
            toilets = JSONArray()
        }

        override fun doInBackground(vararg params: Void?): String {
            // 서울시 데이터는 최대 1000 개씩 가져올수 있기 때문에
            // step 만큼 startIndex 와 lastIndex 값을 변경하며 여러번 호출해야 함.
            val step = 1000
            var startIndex = 1
            var lastIndex = step
            var totalCount = 0
            do {
                // 백그라운드 작업이 취소된 경우 루프를 빠져나간다.
                if (isCancelled) break
                // totalCount 가 0 이 아닌 경우 최초 실행이 아니므로 step 만큼 startIndex 와 lastIndex 를 증가
                if (totalCount != 0) {
                    startIndex += step
                    lastIndex += step
                }
                // startIndex, lastIndex 로 데이터 조회
                val jsonObject = readData(startIndex, lastIndex)
                // totalCount 를 가져온다.
                totalCount = jsonObject.getJSONObject("SearchPublicToiletPOIService").getInt("list_total_count")
                // 서비스센터 정보 데이터 집합을 가져온다.
                val rows = jsonObject.getJSONObject("SearchPublicToiletPOIService").getJSONArray("row")
                // 기존에 읽은 데이터와 병합
                toilets.merge(rows)
                // UI 업데이트를 위해 progress 발행
                publishProgress(rows)
            } while (lastIndex < totalCount) // lastIndex 가 총 개수보다 적으면 반복한다
            return "complete"
        }

        // 데이터를 읽어올때마다 중간중간 실행
        override fun onProgressUpdate(vararg values: JSONArray?) {
            // vararg 는 JSONArray 파라미터를 가변적으로 전달하도록 하는 키워드
            // 인덱스 0의 데이터를 사용
            val array = values[0]
            array?.let {
                for (i in 0 until array.length()) {
                    // 마커 추가
                    addMarkers(array.getJSONObject(i))
                }
            }
        }
    }

    // 앱이 활성화될때 서울시 데이터를 읽어옴
    override fun onStart() {
        super.onStart()
        task?.cancel(true)
        task = ToiletReadTask()
        task?.execute()
    }

    // 앱이 비활성화 될때 백그라운드 작업 취소
    override fun onStop() {
        super.onStop()
        task?.cancel(true)
        task = null
    }

    // 마커를 추가하는 함수
    fun addMarkers(toilet: JSONObject) {
        mMap?.addMarker(
            MarkerOptions()
                .position(LatLng(toilet.getDouble("Y_WGS84"), toilet.getDouble("X_WGS84")))
                .title(toilet.getString("FNAME"))
                .snippet(toilet.getString("ANAME"))
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        )
    }

}
