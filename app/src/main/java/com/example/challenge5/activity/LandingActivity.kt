package com.example.challenge5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge5.adapter.ViewPagerAdapter
import com.example.challenge5.databinding.ActivityLandingBinding
import com.example.challenge5.fragment.LandingFragment3



class LandingActivity : AppCompatActivity() {

    companion object{
        private const val ONE_FRAGMENT = 1
    }

    private var binding: ActivityLandingBinding? = null

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

        binding?.ivNextFragment?.setOnClickListener {
            nextButton(viewPagerAdapter)
        }
    }

    private fun nextButton(viewPagerAdapter: ViewPagerAdapter){
        if (binding?.vpLanding?.currentItem == viewPagerAdapter.itemCount - ONE_FRAGMENT) {

            binding?.vpLanding?.let {
                (supportFragmentManager.fragments[it.currentItem] as LandingFragment3).goToMenuActivity()
            }

        } else {
            binding?.vpLanding.let {
                it?.currentItem = it?.currentItem!! + ONE_FRAGMENT
            }
        }
    }
}