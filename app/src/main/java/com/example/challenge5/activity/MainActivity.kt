package com.example.challenge5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.challenge5.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        loadImage()
        GlobalScope.launch {
            delay(3000L)
            goToSplashScreen()
            finish()
        }

    }

    private fun loadImage() {
        val ivSplashLogo = findViewById<ImageView>(R.id.ivSplashLogo)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(ivSplashLogo)
    }

    private fun goToSplashScreen() {
        val intent = Intent(this@MainActivity, LandingActivity::class.java)
        startActivity(intent)
    }
}