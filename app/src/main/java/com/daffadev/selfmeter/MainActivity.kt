package com.daffadev.selfmeter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        val btnStart : Button = findViewById(R.id.btnStart)
        val etName : EditText = findViewById(R.id.etName)


        btnStart.setOnClickListener{
            if (etName.text.isEmpty()){
                Toast.makeText(this,"Please enter your Name",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this,QuizQuestion::class.java)
//                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                startActivity(intent)
                finish()

            }
        }

    }
}