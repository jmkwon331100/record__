package com.example.myrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //회원가입 버튼을 누를 경우
        btn_register2.setOnClickListener {
            if((!et_id.text.toString().isNullOrBlank())&&
                (!et_pass.text.toString().isNullOrBlank())&&
                (!et_name.text.toString().isNullOrBlank())&&
                (!et_age.text.toString().isNullOrBlank())){
                val myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
            } else {
                Toast.makeText(this,"빈칸을 입력하세요.",Toast.LENGTH_LONG).show()
            }
        }//로그인 화면으로 이동
    }
}
