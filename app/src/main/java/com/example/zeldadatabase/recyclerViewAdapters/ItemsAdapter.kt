package com.example.zeldadatabase.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.additionalClasses.ItemFromRecycleView
import com.example.zeldadatabase.dialogClasses.GameObjectDialog
import com.example.zeldadatabase.showDataActivity.ShowDataView

class ItemsAdapter(val itemDataList: ArrayList<ItemFromRecycleView>, val showDataView: ShowDataView) : RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemHolder(layoutInflater.inflate(R.layout.itemfromrecyclerview, parent, false), showDataView)
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.render(itemDataList[position])
    }

    class ItemHolder(val view: View, val showDataView: ShowDataView) : RecyclerView.ViewHolder(view) {
        fun render(itemFromRecycleView: ItemFromRecycleView) {
            view.findViewById<TextView>(R.id.rvNameTextView).text = itemFromRecycleView.title
            view.findViewById<RecyclerView>(R.id.gameObjectDataRecyclerView).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val recyclerViewAdapter = GameObjectAdapter(itemFromRecycleView.items) { gameObject ->
                    itemFromRecycleView.items.forEach {
                        if (gameObject.title == it.title) {
                            showGameObjectInfo(gameObject)
                        }
                    }
                }
                adapter = recyclerViewAdapter
            }
        }


        fun showGameObjectInfo(gameObject: GameObject) {
            val gameObjectDialog = GameObjectDialog(gameObject)
            gameObjectDialog.show(showDataView.supportFragmentManager, "gameobject dialog")
        }
    }
}