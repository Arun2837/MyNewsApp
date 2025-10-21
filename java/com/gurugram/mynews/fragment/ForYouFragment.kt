package com.gurugram.mynews.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gurugram.mynews.R
import com.gurugram.mynews.adapter.HomePageAdapter
import com.gurugram.mynews.model.HomePageList
import com.gurugram.mynews.model.HorizontalList


class ForYouFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomePageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_for_you, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val itemList = listOf(
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            ),
            HomePageList(
                R.drawable.profile_selected,
                R.drawable.join_as_creator_group,
                11,
                "Arun Singh",
                "कांग्रेस CEC ने करीब 40 उम्मीदवारों के नाम पर लगाई मुहर, जल्द आएगी पहली सूची",
                "नोएडा उत्तर प्रदेश:"
            )
        )

        val list = listOf(
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            ),
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            ),
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            ),
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            ),
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            ),
            HorizontalList(
                R.drawable.ma_durga,
                R.drawable.app_launcher_1024_1024,
                "आज मां दुर्गा आएं आपके द्वार, ."
            )
        )


        adapter = HomePageAdapter(itemList, list)
        recyclerView.adapter = adapter

    }


}