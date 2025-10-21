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


class TreadingFragment : Fragment() {

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

        val list = emptyList<HorizontalList>()

        adapter = HomePageAdapter(itemList,list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }


}