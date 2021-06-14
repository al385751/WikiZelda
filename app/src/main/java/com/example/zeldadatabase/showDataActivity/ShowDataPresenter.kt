package com.example.zeldadatabase.showDataActivity

import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.additionalClasses.ItemFromRecycleView
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*
import com.example.zeldadatabase.modelStuff.Model

class ShowDataPresenter (val view: ShowDataView, val model: Model) {

    var desiredGameObjects = mutableListOf<ItemFromRecycleView>()

    init {
        view.progressBarVisible = true
        view.recycleViewVisible = false
    }

    fun getCharacters(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getCharacters(Response.Listener<ArrayList<Character>> { characterData ->
            if (characterData != null) {
                characterData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("CHARACTERS", gameObjectData as ArrayList<GameObject>))
                desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                desiredGameObjects[0].items.sortBy { it.title }

                if (!view.monsters && !view.bosses && !view.places && !view.items && !view.dungeons) {
                    view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                    view.recycleViewVisible = true
                    view.progressBarVisible = false
                }

                else if (view.monsters) {
                    getMonsters(game)
                }

                else if (view.bosses) {
                    getBosses(game)
                }

                else if (view.places) {
                    getPlaces(game)
                }

                else if (view.items) {
                    getItems(game)
                }

                else {
                    getDungeons(game)
                }

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }

    fun getMonsters(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getMonsters(Response.Listener<ArrayList<Monster>> { monsterData ->
            if (monsterData != null) {
                monsterData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("MONSTERS", gameObjectData as ArrayList<GameObject>))
                if (view.characters) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[1].items.sortBy { it.title }
                }

                else {
                    desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[0].items.sortBy { it.title }
                }

                if (!view.bosses && !view.places && !view.items && !view.dungeons) {
                    view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                    view.recycleViewVisible = true
                    view.progressBarVisible = false
                }

                else if (view.bosses) {
                    getBosses(game)
                }

                else if (view.places) {
                    getPlaces(game)
                }

                else if (view.items) {
                    getItems(game)
                }

                else {
                    getDungeons(game)
                }

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }

    fun getBosses(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getBosses(Response.Listener<ArrayList<FinalBoss>> { bossData ->
            if (bossData != null) {
                bossData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("BOSSES", gameObjectData as ArrayList<GameObject>))

                if (view.characters && view.monsters) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[2].items.sortBy { it.title }
                }

                else if (view.characters || view.monsters) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[1].items.sortBy { it.title }
                }

                else {
                    desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[0].items.sortBy { it.title }
                }

                if (!view.places && !view.items && !view.dungeons) {
                    view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                    view.recycleViewVisible = true
                    view.progressBarVisible = false
                }

                else if (view.places) {
                    getPlaces(game)
                }

                else if (view.items) {
                    getItems(game)
                }

                else {
                    getDungeons(game)
                }

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }

    fun getPlaces(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getPlaces(Response.Listener<ArrayList<Place>> { placeData ->
            if (placeData != null) {
                placeData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("PLACES", gameObjectData as ArrayList<GameObject>))

                if (view.characters && view.monsters && view.bosses) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[3].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters) || (view.characters && view.bosses) || (view.monsters && view.bosses)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[2].items.sortBy { it.title }
                }

                else if (view.characters || view.monsters || view.bosses) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[1].items.sortBy { it.title }
                }

                else {
                    desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[0].items.sortBy { it.title }
                }

                if (!view.items && !view.dungeons) {
                    view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                    view.recycleViewVisible = true
                    view.progressBarVisible = false
                }

                else if (view.items) {
                    getItems(game)
                }

                else {
                    getDungeons(game)
                }

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }

    fun getItems(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getItems(Response.Listener<ArrayList<Item>> { itemData ->
            if (itemData != null) {
                itemData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("ITEMS", gameObjectData as ArrayList<GameObject>))

                if (view.characters && view.monsters && view.bosses && view.places) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[3], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[4].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters && view.bosses) || (view.characters && view.monsters && view.places) || (view.characters && view.bosses && view.places) ||
                    (view.monsters && view.bosses && view.places)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[3].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters) || (view.characters && view.bosses) || (view.characters && view.places) || (view.monsters && view.bosses) || (view.monsters && view.places) || (view.bosses && view.places)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[2].items.sortBy { it.title }
                }

                else if (view.characters || view.monsters || view.bosses || view.places) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[1].items.sortBy { it.title }
                }

                else {
                    desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[0].items.sortBy { it.title }
                }

                if (!view.dungeons) {
                    view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                    view.recycleViewVisible = true
                    view.progressBarVisible = false
                }

                else {
                    getDungeons(game)
                }

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }

    fun getDungeons(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getDungeons(Response.Listener<ArrayList<Dungeon>> { dungeonData ->
            if (dungeonData != null) {
                dungeonData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("DUNGEONS", gameObjectData as ArrayList<GameObject>))

                if (view.characters && view.monsters && view.bosses && view.places && view.items) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[3], desiredGameObjects[4], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[5].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters && view.bosses && view.places) || (view.characters && view.bosses && view.places && view.items) || (view.characters && view.monsters && view.places && view.items) ||
                    (view.characters && view.monsters && view.bosses && view.items) || (view.monsters && view.bosses && view.places && view.items)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[3], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[4].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters && view.bosses) || (view.characters && view.monsters && view.places) || (view.characters && view.monsters && view.items)
                    || (view.characters && view.bosses && view.places) || (view.characters && view.bosses && view.items) || (view.characters && view.places && view.items)
                    || (view.monsters && view.bosses && view.places) || (view.monsters && view.bosses && view.items) || (view.bosses && view.places && view.items) || (view.monsters && view.places && view.items)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[2], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[3].items.sortBy { it.title }
                }

                else if ((view.characters && view.monsters) || (view.characters && view.bosses) || (view.characters && view.places) || (view.characters && view.items) || (view.monsters && view.bosses)
                    || (view.monsters && view.places) || (view.monsters && view.items) || (view.bosses && view.places) || (view.bosses && view.items) && (view.places && view.items)) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[1], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[2].items.sortBy { it.title }
                }

                else if (view.characters || view.monsters || view.bosses || view.places || view.items) {
                    desiredGameObjects = mutableListOf(desiredGameObjects[0], desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[1].items.sortBy { it.title }
                }

                else {
                    desiredGameObjects = mutableListOf(desiredGameObjects[desiredGameObjects.count() - 1])
                    desiredGameObjects[0].items.sortBy { it.title }
                }

                view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)
                view.recycleViewVisible = true
                view.progressBarVisible = false

            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)
    }
}