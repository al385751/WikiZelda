package com.example.zeldadatabase.modelStuff

import android.content.Context
import com.android.volley.Response
import com.example.zeldadatabase.additionalClasses.Game

class Model(context: Context) {
    private val network = Network.getInstance(context)

    fun getGames(listener: Response.Listener<List<Game>>, errorListener: Response.ErrorListener) {
        network.getGames(listener, errorListener)
    }
}