package com.example.zeldadatabase.additionalClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R

class ItemsAdapter(val itemDataList: ArrayList<ItemFromRecycleView>) : RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(R.layout.itemfromrecyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ItemHolder, position: Int) {
        holder.render(itemDataList[position])
    }

    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(itemFromRecycleView: ItemFromRecycleView) {
            view.findViewById<TextView>(R.id.rvNameTextView).text = itemFromRecycleView.title
            view.findViewById<RecyclerView>(R.id.gameObjectDataRecyclerView).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val recyclerViewAdapter = GameObjectAdapter(itemFromRecycleView.items)
                adapter = recyclerViewAdapter
            }
        }
    }
}