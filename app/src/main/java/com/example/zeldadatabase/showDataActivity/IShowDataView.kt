package com.example.zeldadatabase.showDataActivity

import android.view.View
import com.example.zeldadatabase.recyclerViewAdapters.ItemFromRecycleView

interface IShowDataView {
    var progressBarVisible: Boolean

    fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>)
    fun setCurrentImage()
    fun showError(message: String)
    fun showGame(view: View)
}