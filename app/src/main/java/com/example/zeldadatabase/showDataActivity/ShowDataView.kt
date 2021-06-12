package com.example.zeldadatabase.showDataActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.recyclerViewAdapters.ItemFromRecycleView
import com.example.zeldadatabase.recyclerViewAdapters.ItemsAdapter
import com.example.zeldadatabase.modelStuff.Model
import kotlin.math.log

class ShowDataView : AppCompatActivity(), IShowDataView {

    var game : Game? = null
    var characters : Boolean = false
    var monsters : Boolean = false
    var bosses : Boolean = false
    var places : Boolean = false
    var items : Boolean = false
    var dungeons : Boolean = false

    lateinit var nameGameTextView: TextView
    lateinit var gameImageView: ImageView

    lateinit var progressBar: ProgressBar
    lateinit var rvItems: RecyclerView

    lateinit var presenter : ShowDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_showdata_main)

        game = intent.getParcelableExtra("Game")

        progressBar = findViewById(R.id.progressBar2)
        rvItems = findViewById(R.id.itemDataRecyclerView)

        nameGameTextView = findViewById(R.id.nameGameTextView)
        gameImageView = findViewById(R.id.gameImageView)

        val model = Model(applicationContext)
        presenter = ShowDataPresenter(this, model)

        nameGameTextView.text = game.toString()

        val gameObject1 = GameObject("ITEM 1", "Este es el item 1")
        val gameObject2 = GameObject("ITEM 2", "Este es el item 2")
        val gameObject3 = GameObject("ITEM 3", "Este es el item 3")
        val gameObject4 = GameObject("ITEM 4", "Este es el item 4")

        val item1 = ItemFromRecycleView("CHARACTERS", mutableListOf(gameObject1, gameObject2) as ArrayList<GameObject>)
        val item2 = ItemFromRecycleView("DUNGEONS", mutableListOf(gameObject3, gameObject4, gameObject1) as ArrayList<GameObject>)

        val items = mutableListOf(item1, item2) as ArrayList<ItemFromRecycleView>

        createRecyclerView(items)
        showImage()
    }

    override var progressBarVisible : Boolean
        get() = progressBar.visibility == View.VISIBLE
        set(value) {
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>) {
        val adapter = ItemsAdapter(itemList)
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
    }

    override fun showImage() {
        when {
            game.toString() == "The Legend of Zelda" -> gameImageView.setImageResource(R.drawable.thelegendofzelda)
            game.toString() == "The Legend of Zelda: A Link to the Past" -> gameImageView.setImageResource(R.drawable.alinktothepast)
            game.toString() == "The Legend of Zelda: Oracle of Ages" -> gameImageView.setImageResource(R.drawable.oracleofages)
            game.toString() == "The Legend of Zelda: Ocarina of Time" -> gameImageView.setImageResource(R.drawable.ocarinaoftime)
            game.toString() == "The Legend of Zelda: Link's Awakening DX" -> gameImageView.setImageResource(R.drawable.linksawakeningdx)
            game.toString() == "The Legend of Zelda: Majora's Mask" -> gameImageView.setImageResource(R.drawable.majorasmask)
            game.toString() == "Zelda II: The Adventure of Link" -> gameImageView.setImageResource(R.drawable.theadventureoflink)
            game.toString() == "The Legend of Zelda: The Wind Waker" -> gameImageView.setImageResource(R.drawable.windwaker)
            game.toString() == "The Legend of Zelda: Twilight Princess" -> gameImageView.setImageResource(R.drawable.twilightprincess)
            game.toString() == "The Legend of Zelda: Oracle of Seasons" -> gameImageView.setImageResource(R.drawable.oracleofseasons)
            game.toString() == "The Legend of Zelda: Spirit Tracks" -> gameImageView.setImageResource(R.drawable.spirittracks)
            game.toString() == "BS The Legend of Zelda: Ancient Stone Tablets" -> gameImageView.setImageResource(R.drawable.ancientstonetablets)
            game.toString() == "Hyrule Warriors" -> gameImageView.setImageResource(R.drawable.hyrulewarriors)
            game.toString() == "The Legend of Zelda: Four Swords Adventures" -> gameImageView.setImageResource(R.drawable.fourswordsadventures)
            game.toString() == "The Legend of Zelda: Breath of the Wild" -> gameImageView.setImageResource(R.drawable.breathofthewild)
            game.toString() == "The Legend of Zelda: Tri Force Heroes" -> gameImageView.setImageResource(R.drawable.triforceheroes)
            game.toString() == "Zelda: The Wand of Gamelon" -> gameImageView.setImageResource(R.drawable.wandofgamelon)
            game.toString() == "The Legend of Zelda: Four Swords" -> gameImageView.setImageResource(R.drawable.fourswords)
            game.toString() == "The Legend of Zelda: The Minish Cap" -> gameImageView.setImageResource(R.drawable.theminishcap)
            game.toString() == "The Legend of Zelda: Four Swords Anniversary Edition" -> gameImageView.setImageResource(R.drawable.fourswordsanniversary)
            game.toString() == "Hyrule Warriors: Age of Calamity" -> gameImageView.setImageResource(R.drawable.ageofcalamity)
            game.toString() == "Link: The Faces of Evil" -> gameImageView.setImageResource(R.drawable.thefacesofevil)
            game.toString() == "The Legend of Zelda: Phantom Hourglass" -> gameImageView.setImageResource(R.drawable.phantomhourglass)
            game.toString() == "The Legend of Zelda: Link's Awakening" -> gameImageView.setImageResource(R.drawable.linksawakening)
            game.toString() == "The Legend of Zelda: A Link Between Worlds" -> gameImageView.setImageResource(R.drawable.alinkbetweenworlds)
            game.toString() == "The Legend of Zelda: Skyward Sword" -> gameImageView.setImageResource(R.drawable.skywardsword)
            game.toString() == "BS The Legend of Zelda" -> gameImageView.setImageResource(R.drawable.bsthelegendofzelda)
            game.toString() == "Freshly-Picked Tingle's Rosy Rupeeland" -> gameImageView.setImageResource(R.drawable.tinglerosyrupeeland)
            game.toString() == "Zelda's Adventure" -> gameImageView.setImageResource(R.drawable.zeldasadventure)
            game.toString() == "Hyrule Warriors Legends" -> gameImageView.setImageResource(R.drawable.hyrulewarriorslegends)
            game.toString() == "Link's Crossbow Training" -> gameImageView.setImageResource(R.drawable.crossbowtraining)
        }
    }
}