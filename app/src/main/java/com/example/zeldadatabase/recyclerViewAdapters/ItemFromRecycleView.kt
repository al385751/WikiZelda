package com.example.zeldadatabase.recyclerViewAdapters

import com.example.zeldadatabase.additionalClasses.GameObject

class ItemFromRecycleView(_name: String, _items: ArrayList<GameObject>) {
    val title = _name
    val items = _items
}