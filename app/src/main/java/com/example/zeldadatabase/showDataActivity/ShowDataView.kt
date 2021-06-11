package com.example.zeldadatabase.showDataActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.additionalClasses.ItemFromRecycleView
import com.example.zeldadatabase.additionalClasses.ItemsAdapter
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
    lateinit var rvItems: RecyclerView

    lateinit var presenter : ShowDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_showdata_main)

        game = intent.getParcelableExtra("Game")

        progressBar = findViewById(R.id.progressBar2)
        rvItems = findViewById(R.id.itemDataRecyclerView)

        val model = Model(applicationContext)
        presenter = ShowDataPresenter(this, model)

        val gameObject1 = GameObject("ITEM 1", "Este es el item 1")
        val gameObject2 = GameObject("ITEM 2", "Este es el item 2")
        val gameObject3 = GameObject("ITEM 3", "Este es el item 3")
        val gameObject4 = GameObject("ITEM 4", "Este es el item 4")

        val item1 = ItemFromRecycleView("CHARACTERS", mutableListOf(gameObject1, gameObject2) as ArrayList<GameObject>)
        val item2 = ItemFromRecycleView("DUNGEONS", mutableListOf(gameObject3, gameObject4, gameObject1) as ArrayList<GameObject>)

        val items = mutableListOf<ItemFromRecycleView>(item1, item2) as ArrayList<ItemFromRecycleView>
        createRecyclerView(items)
    }

    override var progressBarVisible : Boolean
        get() = progressBar.visibility == View.VISIBLE
        set(value) {
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>) {
        val adapter = ItemsAdapter(itemList)
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
    }
}