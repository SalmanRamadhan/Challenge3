package com.example.challenge5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.viewpager2.widget.ViewPager2
import com.example.challenge5.R
import com.example.challenge5.adapter.ViewPagerAdapter
import com.example.challenge5.databinding.ActivityLandingBinding
import com.example.challenge5.fragment.LandingFragment3

class LandingActivity : AppCompatActivity() {

    var binding: ActivityLandingBinding? = null


    val ONEFRAGMENT = 1


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
        if (binding?.vpLanding?.currentItem == viewPagerAdapter.itemCount - ONEFRAGMENT) {

            binding?.vpLanding?.let {
                (supportFragmentManager.fragments[it.currentItem] as LandingFragment3).goToMenuActivity()
            }

        } else {
            binding?.vpLanding.let {
                it?.currentItem = it?.currentItem!! + ONEFRAGMENT
            }
        }
    }
}