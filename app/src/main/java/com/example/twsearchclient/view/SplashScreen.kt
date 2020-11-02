package com.example.twsearchclient.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.twsearchclient.R
import com.example.twsearchclient.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var splashScreenBinding: ActivitySplashScreenBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_splash_screen
        )
        callMeta()

    }

    private fun callMeta() {
        val handler = Handler()
        handler.postDelayed({ goMainScreen() }, 3000)
    }

    private fun goMainScreen() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
    }
}