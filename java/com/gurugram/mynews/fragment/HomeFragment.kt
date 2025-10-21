package com.gurugram.mynews.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gurugram.mynews.R
import com.gurugram.mynews.adapter.ViewPagerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewPager: ViewPager2
    private lateinit var homeTabLayout: TabLayout

    private lateinit var bottomNavigationHome: BottomNavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewPager = view.findViewById(R.id.home_view_pager)
        homeTabLayout = view.findViewById(R.id.home_tab_layout)
        bottomNavigationHome = view.findViewById(R.id.bottomNavigationView)

//        homeTabLayout.addTab(homeTabLayout.newTab().setText("For You"))
//        homeTabLayout.addTab(homeTabLayout.newTab().setText("Treading"))
//        homeTabLayout.addTab(homeTabLayout.newTab().setText("Following"))

        val adapter = ViewPagerAdapter(childFragmentManager,viewLifecycleOwner.lifecycle)
        homeViewPager.adapter = adapter
        homeViewPager.currentItem = 0

        val tabTitles = arrayOf("For You", "Treading", "Following")
        TabLayoutMediator(homeTabLayout, homeViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        bottomNavigationHome.setOnItemSelectedListener {item ->
            when (item.itemId){
                R.id.home_icon -> {
                    childFragmentManager.popBackStack()
                    view.findViewById<FrameLayout>(R.id.home_fragment_container)?.visibility = View.GONE
                    true
                }
                R.id.home_profile_icon -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.home_notification_icon -> {
                    loadFragment(NotificationFragment())
                    true
                }
                R.id.home_creator_icon -> {
                    loadFragment(NewsCreatorFragment())
                    true
                }

                R.id.home_web_stories -> {
                    loadFragment(WebStoriesFragment())
                    true
                }
                else -> false

            }

        }
    }

    private fun loadFragment(fragment: Fragment) {

        val container = view?.findViewById<FrameLayout>(R.id.home_fragment_container)
        container?.visibility = View.VISIBLE
        childFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}