package com.example.zeldadatabase.modelStuff

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*
import com.example.zeldadatabase.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class Model(context: Context) {
    private val network = Network.getInstance(context)
    private val database : MyDatabase = MyDatabase.getInstance(context)

    fun getGames(listener: Response.Listener<List<Game>>, errorListener: Response.ErrorListener) =
        GlobalScope.launch(Dispatchers.Main) {
            val games = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por los juegos")
                database.dao.getGames()
            }
            if (games.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getGames(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertGames(it)
                    }
                    listener.onResponse(it)
                }, errorListener)
            }
            else
                listener.onResponse(games)
        }

    fun getCharacters(listener: Response.Listener<ArrayList<Character>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val characters = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por los personajes del juego")
                database.dao.getCharacters(gameId)
            }
            if (characters.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getCharacters(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertCharacters(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(characters as ArrayList<Character>)
        }

    fun getMonsters(listener: Response.Listener<ArrayList<Monster>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val monsters = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por los monstruos del juego")
                database.dao.getMonsters(gameId)
            }
            if (monsters.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getMonsters(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertMonsters(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(monsters as ArrayList<Monster>)
        }

    fun getBosses(listener: Response.Listener<ArrayList<FinalBoss>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val bosses = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por los jefes finales del juego")
                database.dao.getBosses(gameId)
            }
            if (bosses.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getBosses(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertBosses(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(bosses as ArrayList<FinalBoss>)
        }

    fun getPlaces(listener: Response.Listener<ArrayList<Place>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val places = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por las ubicaciones del juego")
                database.dao.getPlaces(gameId)
            }
            if (places.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getPlaces(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertPlaces(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(places as ArrayList<Place>)
        }

    fun getItems(listener: Response.Listener<ArrayList<Item>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por los objetos del juego")
                database.dao.getItems(gameId)
            }
            if (items.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getItems(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertItems(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(items as ArrayList<Item>)
        }

    fun getDungeons(listener: Response.Listener<ArrayList<Dungeon>>, errorListener: Response.ErrorListener, gameId: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val dungeons = withContext(Dispatchers.IO) {
                Log.e("ZELDA DATABASE", "Voy a acceder a la base de datos a por las mazmorras del juego")
                database.dao.getDungeons(gameId)
            }
            if (dungeons.isEmpty()) {
                Log.e("ZELDA DATABASE", "No hay nada en la base de datos, guardando nuevos datos...")
                network.getDungeons(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertDungeons(it)
                    }
                    listener.onResponse(it)
                }, errorListener, gameId)
            }
            else
                listener.onResponse(dungeons as ArrayList<Dungeon>)
        }
}