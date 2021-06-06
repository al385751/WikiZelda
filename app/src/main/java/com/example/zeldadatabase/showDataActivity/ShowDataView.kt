package com.example.zeldadatabase.showDataActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.modelStuff.Model

class ShowDataView : AppCompatActivity(), IShowDataView {

    var game : Game? = null
    var characters : Boolean = false
    var monsters : Boolean = false
    var bosses : Boolean = false
    var places : Boolean = false
    var items : Boolean = false
    var dungeons : Boolean = false

    lateinit var progressBar: ProgressBar

    lateinit var presenter : ShowDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_showdata_main)

        game = intent.getParcelableExtra("Game")
        Log.e("ZELDA DATABASE", game.toString())

        progressBar = findViewById(R.id.progressBar2)

        val model = Model(applicationContext)
        presenter = ShowDataPresenter(this, model)
    }

    override var progressBarVisible : Boolean
        get() = progressBar.visibility == View.VISIBLE
        set(value) {
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }
}