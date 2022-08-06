package com.example.challenge5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge5.R
import com.example.challenge5.adapter.ViewPagerAdapter
import com.example.challenge5.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    var binding : ActivityLandingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        val viewPagerAdapter = ViewPagerAdapter(this)
        binding?.let {
            it.vpLanding.adapter = viewPagerAdapter
            it.ciLanding.setViewPager(it.vpLanding)
        }



    }
}