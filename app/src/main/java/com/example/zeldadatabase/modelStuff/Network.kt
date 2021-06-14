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
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*
import org.json.JSONException
import org.json.JSONObject

private const val BASE_URL = "https://zelda-api.apius.cc/api"
private const val GAMES = "games"
private const val CHARACTERS = "characters"
private const val MONSTERS = "monsters"
private const val BOSSES = "bosses"
private const val PLACES = "places"
private const val ITEMS = "items"
private const val DUNGEONS = "dungeons"

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
                        characters.add(Character(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        characters.sortBy { it.title }
        listener.onResponse(characters)
    }

    fun getMonsters(listener: Response.Listener<ArrayList<Monster>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 17) {
            val url = "$BASE_URL/$MONSTERS?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response -> processMonsters(response, listener, gameId) },
                { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processMonsters(response: JSONObject, listener: Response.Listener<ArrayList<Monster>>, gameId: String) {
        val monsters = ArrayList<Monster>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val appearances = gameObject.getJSONArray(APPEARANCES)
                for (j in 0 until appearances.length()) {
                    if (appearances[j] == "$BASE_URL/$GAMES/$gameId") {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        monsters.add(Monster(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        monsters.sortBy { it.title }
        listener.onResponse(monsters)
    }

    fun getBosses(listener: Response.Listener<ArrayList<FinalBoss>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 6) {
            val url = "$BASE_URL/$BOSSES?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response -> processBosses(response, listener, gameId) },
                { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processBosses(response: JSONObject, listener: Response.Listener<ArrayList<FinalBoss>>, gameId: String) {
        val bosses = ArrayList<FinalBoss>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val appearances = gameObject.getJSONArray(APPEARANCES)
                for (j in 0 until appearances.length()) {
                    if (appearances[j] == "$BASE_URL/$GAMES/$gameId") {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        bosses.add(FinalBoss(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        bosses.sortBy { it.title }
        listener.onResponse(bosses)
    }

    fun getPlaces(listener: Response.Listener<ArrayList<Place>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 30) {
            val url = "$BASE_URL/$PLACES?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response -> processPlaces(response, listener, gameId) },
                { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processPlaces(response: JSONObject, listener: Response.Listener<ArrayList<Place>>, gameId: String) {
        val places = ArrayList<Place>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val appearances = gameObject.getJSONArray(APPEARANCES)
                for (j in 0 until appearances.length()) {
                    if (appearances[j] == "$BASE_URL/$GAMES/$gameId") {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        places.add(Place(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        places.sortBy { it.title }
        listener.onResponse(places)
    }

    fun getItems(listener: Response.Listener<ArrayList<Item>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 38) {
            val url = "$BASE_URL/$ITEMS?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response -> processItems(response, listener, gameId) },
                { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processItems(response: JSONObject, listener: Response.Listener<ArrayList<Item>>, gameId: String) {
        val items = ArrayList<Item>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val games = gameObject.getJSONArray(GAMES)
                for (j in 0 until games.length()) {
                    if (games[j] == gameId) {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        items.add(Item(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        items.sortBy { it.title }
        listener.onResponse(items)
    }

    fun getDungeons(listener: Response.Listener<ArrayList<Dungeon>>, errorListener: Response.ErrorListener, gameId: String) {
        for (i in 1 until 8) {
            val url = "$BASE_URL/$DUNGEONS?$LIMIT=50&$PAGE=$i"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response -> processDungeons(response, listener, gameId) },
                { error -> errorListener.onErrorResponse(error) })
            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(10000000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun processDungeons(response: JSONObject, listener: Response.Listener<ArrayList<Dungeon>>, gameId: String) {
        val dungeons = ArrayList<Dungeon>()
        try {
            val gameArray = response.getJSONArray(DATA)
            for (i in 0 until gameArray.length()) {
                val gameObject : JSONObject = gameArray[i] as JSONObject
                val appearances = gameObject.getJSONArray(APPEARANCES)
                for (j in 0 until appearances.length()) {
                    if (appearances[j] == "$BASE_URL/$GAMES/$gameId") {
                        val name = gameObject.getString(NAME)
                        val description = gameObject.getString(DESCRIPTION_LABEL)
                        dungeons.add(Dungeon(name, description, gameId))
                    }
                }
            }
        } catch (o : JSONException) {
            listener.onResponse(null)
        }

        dungeons.sortBy { it.title }
        listener.onResponse(dungeons)
    }
}