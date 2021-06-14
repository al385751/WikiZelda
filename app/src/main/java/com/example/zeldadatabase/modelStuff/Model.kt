package com.example.zeldadatabase.modelStuff

import android.content.Context
import com.android.volley.Response
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Character
import java.util.*

class Model(context: Context) {
    private val network = Network.getInstance(context)

    fun getGames(listener: Response.Listener<List<Game>>, errorListener: Response.ErrorListener) {
        network.getGames(listener, errorListener)
    }

    fun getCharacters(listener: Response.Listener<ArrayList<Character>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getCharacters(listener, errorListener, gameId)
    }
}