package com.example.myrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.record06.RecipeAdapter
import kotlinx.android.synthetic.main.activity_recom.*

class RecomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recom)


        //리사이클러뷰에 어댑터 설정
        recipe_list.adapter = RecipeAdapter(this)

        /* 리사이클러뷰에 레이아웃메니저 설정
           - 리사이클러뷰의 각 아이템을 배치하고, 뷰홀더 재사용 결정
           - 리사이클러뷰의 리스트출력할 때 모양을 설정할 수 있음(가로,세로,격자,불규칙 모양 지정)
           - 안드로이드에서 지원하는 레이아웃메니저
             1) LinearLayoutManager, 2) GridLayoutManager, 3) staggeredGridLayoutManager
        */
        recipe_list.layoutManager = LinearLayoutManager(this)
    }
}