package com.gurugram.mynews.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gurugram.mynews.fragment.FollowingFragment
import com.gurugram.mynews.fragment.ForYouFragment
import com.gurugram.mynews.fragment.TreadingFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {

    private val fragmentList = listOf(ForYouFragment(), TreadingFragment(), FollowingFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}
//    {
//
//        return when (position) {
//            0 -> ForYouFragment()
//            1 -> TreadingFragment()
//            2 -> FollowingFragment()
//            else -> ForYouFragment()
//        }
