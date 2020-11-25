package com.example.lab07.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab07.R
import com.example.lab07.data.Food

class MainRecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private var data: List<Food> = emptyList()

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(data[position])
                return true
            }

            return false
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val v = layoutInflater.inflate(R.layout.item_food, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.item_food_text_title).text = data[position].name
        holder.view.findViewById<TextView>(R.id.item_food_text_description).text =
            data[position].description
        holder.view.findViewById<ImageView>(R.id.item_food_image).transitionName =
            "food_image_${data[position].id}";
        holder.view.findViewById<TextView>(R.id.item_food_text_description).transitionName =
            "food_description_${data[position].id}";
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(food: Food)
    }

    fun setData(listOfFood: List<Food>){
        data = listOfFood
        notifyDataSetChanged()
    }
}