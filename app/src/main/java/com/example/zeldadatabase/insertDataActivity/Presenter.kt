package com.example.zeldadatabase.insertDataActivity

import com.android.volley.Response
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.modelStuff.Model

class Presenter(val view: IMainView, val model: Model) {
    private var actualGame: Game? = null

    init {
        //view.searchEnabled = false
        view.progressBarVisible = true
        view.everythingVisible = false

        model.getGames(Response.Listener<List<Game>> { games ->
            if (games != null) {
                view.showGames(games)
                view.progressBarVisible = false
                view.everythingVisible = true
            } else {
                view.showError("No games in the returned list (possible BAD JSON)")
            }
        }, Response.ErrorListener { error -> view.showError(error.toString()) })
    }

    fun setChosenGame(game: Game) {
        this.actualGame = game
    }

    fun getGame() : Game {
        return this.actualGame!!
    }
}