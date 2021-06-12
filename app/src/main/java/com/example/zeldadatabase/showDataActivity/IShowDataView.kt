package com.example.zeldadatabase.showDataActivity

import com.example.zeldadatabase.recyclerViewAdapters.ItemFromRecycleView

interface IShowDataView {
    var progressBarVisible: Boolean

    fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>)
    fun showImage()
}