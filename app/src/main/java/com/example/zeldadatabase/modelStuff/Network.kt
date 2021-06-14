package com.example.zeldadatabase.modelStuff

import android.content.Context
import android.support.v4.os.IResultReceiver
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Character
import org.json.JSONException
import org.json.JSONObject

private const val BASE_URL = "https://zelda-api.apius.cc/api"
private const val GAMES = "games"
private const val CHARACTERS = "characters"

private const val DATA = "data"
private const val LIMIT = "limit"
private const val PAGE = "page"
private const val APPEARANCES = "appearances"

private const val TITLE_LABEL = "name"
private const val ID_LABEL = "_id"
private const val DESCRIPTION_LABEL = "description"
private const val RELEASED_LABEL = "released_date"
private const val NAME = "name"

class Network private constructor(context: Context) {
    companion object: SingletonHolder<Network, Context>(::Network)

    val queue = Volley.newRequestQueue(context)

    fun getGames(listener: Response.Listener<List<Game>>, errorListener: Response.ErrorListener) {
        val url = "$BASE_URL/$GAMES?$LIMIT=50"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response -> processGames(response, listener) },
            { error -> errorListener.onErrorResponse(error) })
        jsonObjectRequest.setRetryPolicy(DefaultRetryPolicy(100000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        queue.add(jsonObjectRequest)
    }

    private fun processGames(response: JSONObject, listener: Response.Listener<List<Game>>) {
        val games = ArrayList<Game>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val id = gameObject.getString(ID_LABEL)
                val title = gameObject.getString(TITLE_LABEL)
                val description = gameObject.getString(DESCRIPTION_LABEL)
                val released = gameObject.getString(RELEASED_LABEL)
                games.add(Game(title, id, description, released))
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        games.sortBy { it.title }
        listener.onResponse(games)
    }

    fun getCharacters(listener: Response.Listener<ArrayList<Character>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 34) {
            val url = "$BASE_URL/$CHARACTERS?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                    { response -> processCharacters(response, listener, gameId) },
                    { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processCharacters(response: JSONObject, listener: Response.Listener<java.util.ArrayList<Character>>, gameId: String) {
        val characters = ArrayList<Character>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val appearances = gameObject.getJSONArray(APPEARANCES)
                for (j in 0 until appearances.length()) {
                    if (appearances[j] == "$BASE_URL/$GAMES/$gameId") {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        characters.add(Character(name, description))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        characters.sortBy { it.title }
        listener.onResponse(characters)
    }
}