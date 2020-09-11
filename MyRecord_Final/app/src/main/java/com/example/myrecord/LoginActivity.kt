package com.example.myrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myrecord.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //회원가입 버튼 누를 경우
        btn_register.setOnClickListener {
            val myIntent1 = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent1)
        }//회원가입창으로 이동

        //로그인 버튼 누를 경우
        btn_login.setOnClickListener {
            //id입력여부 체크
            if((!et_id.text.toString().isNullOrBlank())&&
                (!et_pass.text.toString().isNullOrBlank())){
                val myIntent2 = Intent(this, MainActivity::class.java)
                myIntent2.putExtra(USER_NAME,et_id.text.toString())
                startActivity(myIntent2)
            } else {
                Toast.makeText(this,"아이디/비밀번호를 입력해주세요.",Toast.LENGTH_LONG).show()
            }
        }//메인화면으로 이동

    }
    //final static 타입으로 USER_NAME 상수 선언
    companion object {
        const val USER_NAME = "USER_NAME"
    }
}
