package com.stupa.userDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {

       return when (position) {
            0 ->    UserDetailsListFragment()
            1 ->    UserDetailsListFragment()
            2 ->    UserDetailsListFragment()
            3 ->    UserDetailsListFragment()
            4 ->    UserDetailsListFragment()

           else -> {
               UserDetailsListFragment()
           }
       }

        }
    }
