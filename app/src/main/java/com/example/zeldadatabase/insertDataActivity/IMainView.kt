package com.example.zeldadatabase.insertDataActivity

import android.view.View
import com.example.zeldadatabase.additionalClasses.Game

interface IMainView {
    var searchEnabled: Boolean

    var progressBarVisible: Boolean
    var everythingVisible: Boolean

    fun showError(message : String)
    fun showGames(games : List<Game>)
    fun searchInfo(view: View)
}