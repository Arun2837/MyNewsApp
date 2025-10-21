package com.gurugram.mynews.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gurugram.mynews.R
import com.gurugram.mynews.model.HorizontalList
import java.util.Collections.list

class HorizontalAdapter(private var itemList: List<HorizontalList>): RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val bigImage: ImageView = itemView.findViewById(R.id.horizontal_image)
        val cornerImage: ImageView = itemView.findViewById(R.id.image_corner)
        val bottomText: TextView = itemView.findViewById(R.id.image_textV_view)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.web_story_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemList[position]
        holder.bigImage.setImageResource(item.imageBig)
        holder.cornerImage.setImageResource(item.imageIcon)
        holder.bottomText.text = item.bottomDesc

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newList: List<HorizontalList>) {
        itemList = newList
        notifyDataSetChanged()
    }
}