package com.example.zeldadatabase.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.GameObject

class GameObjectAdapter(val gameObjectItemList: ArrayList<GameObject>, val onClickListener: (GameObject) -> Unit) : RecyclerView.Adapter<GameObjectAdapter.GameObjectHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameObjectHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameObjectHolder(layoutInflater.inflate(R.layout.gameobject_subitem, parent, false))
    }

    override fun getItemCount(): Int {
        return gameObjectItemList.size
    }

    override fun onBindViewHolder(holder: GameObjectHolder, position: Int) {
        holder.render(gameObjectItemList[position])
        holder.itemView.setOnClickListener {
            onClickListener(gameObjectItemList[position])
        }
    }

    class GameObjectHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(gameObject: GameObject) {
            view.findViewById<TextView>(R.id.nameResultTextView).text = gameObject.title
        }
    }
}