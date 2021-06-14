package com.example.zeldadatabase.showDataActivity

import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.additionalClasses.ItemFromRecycleView
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Character
import com.example.zeldadatabase.modelStuff.Model

class ShowDataPresenter (val view: ShowDataView, val model: Model) {

    var desiredGameObjects = mutableListOf<ItemFromRecycleView>()

    init {
        view.progressBarVisible = true
    }

    fun getCharacters(game: Game) {
        val gameObjectData = mutableListOf<GameObject>()
        model.getCharacters(Response.Listener<ArrayList<Character>> { characterData ->
            if (characterData != null) {
                characterData.forEach {
                    gameObjectData.add(it)
                }

                desiredGameObjects.add(ItemFromRecycleView("CHARACTERS", gameObjectData as ArrayList<GameObject>))
                desiredGameObjects = mutableListOf(desiredGameObjects[0])
                desiredGameObjects[0].items.sortBy { it.title }
                view.createRecyclerView(desiredGameObjects as ArrayList<ItemFromRecycleView>)

                view.progressBarVisible = false
            } else {
                view.showError("No data found for the characters (POSSIBLE BAD JSON)")
            }
        }, Response.ErrorListener { error ->
            if (error is VolleyError)
                view.showError("Volley Error Timeout, connection lost with the network")
            else view.showError(error.toString())
        }, game.id)

        Log.e("ZELDA DATABASE", desiredGameObjects.count().toString())
    }
}