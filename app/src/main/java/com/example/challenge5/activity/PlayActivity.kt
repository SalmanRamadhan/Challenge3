package com.example.challenge5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.challenge5.R
import com.example.challenge5.activity.MenuActivity.Companion.userName
import com.example.challenge5.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    var binding: ActivityPlayBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadImage()
        supportActionBar?.hide()
        val name = intent.getStringExtra(MenuActivity.userName)

        binding?.let {
            it.tvPlayer1.text = name
            it.ivExit.setOnClickListener {
                val intent = Intent(this@PlayActivity, MenuActivity::class.java)
                intent.putExtra(userName,name)
                startActivity(intent)
            }
        }

    }

    private fun loadImage() {
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(ivLogo)
    }
}