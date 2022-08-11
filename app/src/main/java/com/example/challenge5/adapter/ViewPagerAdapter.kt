package com.example.challenge5.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challenge5.fragment.LandingFragment
import com.example.challenge5.fragment.LandingFragment2
import com.example.challenge5.fragment.LandingFragment3

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val LANDING_PAGE_COUNT = 3
    }

    override fun getItemCount(): Int {
        return LANDING_PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LandingFragment()
            }
            1 -> {
                LandingFragment2()
            }
            else -> {
                LandingFragment3()
            }
        }
    }


}