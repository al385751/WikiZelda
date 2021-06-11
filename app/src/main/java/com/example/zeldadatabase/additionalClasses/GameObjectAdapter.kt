package com.example.zeldadatabase.additionalClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R

class GameObjectAdapter(val gameObjectItemList: ArrayList<GameObject>) : RecyclerView.Adapter<GameObjectAdapter.GameObjectHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameObjectHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameObjectHolder(layoutInflater.inflate(R.layout.gameobject_subitem, parent, false))
    }

    override fun getItemCount(): Int {
        return gameObjectItemList.size
    }

    override fun onBindViewHolder(holder: GameObjectAdapter.GameObjectHolder, position: Int) {
        holder.render(gameObjectItemList[position])
    }

    class GameObjectHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(gameObject: GameObject) {
            view.findViewById<TextView>(R.id.nameResultTextView).text = gameObject.title
        }
    }
}