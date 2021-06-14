package com.example.zeldadatabase.modelStuff

import android.content.Context
import com.android.volley.Response
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*
import java.util.*

class Model(context: Context) {
    private val network = Network.getInstance(context)

    fun getGames(listener: Response.Listener<List<Game>>, errorListener: Response.ErrorListener) {
        network.getGames(listener, errorListener)
    }

    fun getCharacters(listener: Response.Listener<ArrayList<Character>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getCharacters(listener, errorListener, gameId)
    }

    fun getMonsters(listener: Response.Listener<ArrayList<Monster>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getMonsters(listener, errorListener, gameId)
    }

    fun getBosses(listener: Response.Listener<ArrayList<FinalBoss>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getBosses(listener, errorListener, gameId)
    }

    fun getPlaces(listener: Response.Listener<ArrayList<Place>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getPlaces(listener, errorListener, gameId)
    }

    fun getItems(listener: Response.Listener<ArrayList<Item>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getItems(listener, errorListener, gameId)
    }

    fun getDungeons(listener: Response.Listener<ArrayList<Dungeon>>, errorListener: Response.ErrorListener, gameId: String) {
        network.getDungeons(listener, errorListener, gameId)
    }
}