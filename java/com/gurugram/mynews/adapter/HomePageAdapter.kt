package com.gurugram.mynews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gurugram.mynews.R
import com.gurugram.mynews.model.HomePageList
import com.gurugram.mynews.model.HorizontalList

class HomePageAdapter(private val itemList: List<HomePageList>, private val storyList: List<HorizontalList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_VERTICAL_ITEM = 0
        const val TYPE_HORIZONTAL_LIST = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (storyList.isNotEmpty() && position == 1)
        {
            TYPE_HORIZONTAL_LIST
        }
        else
        {
            TYPE_VERTICAL_ITEM
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HorizontalViewHolder) {
            holder.bind(storyList)
        }
        else if (holder is VerticalViewHolder){
            val actualPos = if (storyList.isNotEmpty() && position > 1) position -1 else position

            if (actualPos in itemList.indices) {
                holder.bind(itemList[actualPos])
            }
        }
    }

    override fun getItemCount(): Int{
        return itemList.size + if (storyList.isNotEmpty()) 1 else 0
    }

    inner class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val userProfileImage: ImageView = itemView.findViewById(R.id.home_profile_image)
            val userName: TextView = itemView.findViewById(R.id.home_profile_name)
            val timeBefore: TextView = itemView.findViewById(R.id.home_time_text)
            val bannerImage: ImageView = itemView.findViewById(R.id.home_banner_image)
            val tileNews: TextView = itemView.findViewById(R.id.home_heading_text)
            val subTileNews: TextView = itemView.findViewById(R.id.home_profile_state)

        fun bind(item: HomePageList) {
            userProfileImage.setImageResource(item.userProfileImage)
            bannerImage.setImageResource(item.bannerImage)
            userName.text = item.userName
            tileNews.text = item.title
            subTileNews.text = item.subTitle
            timeBefore.text = "${item.timeBefore}"

        }

    }

    inner class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.horizontal_recycler_view)

        init {
            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(list: List<HorizontalList>) {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = HorizontalAdapter(list)
            }
            else{
                (recyclerView.adapter as HorizontalAdapter).updateList(list)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HORIZONTAL_LIST) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_horizontal_list, parent, false)
            HorizontalViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_page_verticel_list, parent, false)
            VerticalViewHolder(view)
        }
    }





}