package com.example.mymediaplayer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun gotomusicplayer(view: View){
        val intent :Intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)


    }

}