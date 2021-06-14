package com.example.zeldadatabase.showDataActivity

import android.view.View
import com.example.zeldadatabase.additionalClasses.ItemFromRecycleView

interface IShowDataView {
    var progressBarVisible: Boolean
    var recycleViewVisible: Boolean

    fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>)
    fun setCurrentImage()
    fun showError(message: String)
    fun showGame(view: View)
}