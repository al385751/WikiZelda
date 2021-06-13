package com.example.zeldadatabase.showDataActivity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject
import com.example.zeldadatabase.dialogClasses.GameDialog
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

    var currentGameImage : Int = 0

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
        setCurrentImage()

        gameImageView.setImageResource(currentGameImage)

        title = "Showing Info"
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

    override fun setCurrentImage() {
        when {
            game.toString() == "The Legend of Zelda" -> currentGameImage = R.drawable.thelegendofzelda
            game.toString() == "The Legend of Zelda: A Link to the Past" -> currentGameImage = R.drawable.alinktothepast
            game.toString() == "The Legend of Zelda: Oracle of Ages" -> currentGameImage = R.drawable.oracleofages
            game.toString() == "The Legend of Zelda: Ocarina of Time" -> currentGameImage = R.drawable.ocarinaoftime
            game.toString() == "The Legend of Zelda: Link's Awakening DX" -> currentGameImage = R.drawable.linksawakeningdx
            game.toString() == "The Legend of Zelda: Majora's Mask" -> currentGameImage = R.drawable.majorasmask
            game.toString() == "Zelda II: The Adventure of Link" -> currentGameImage = R.drawable.theadventureoflink
            game.toString() == "The Legend of Zelda: The Wind Waker" -> currentGameImage = R.drawable.windwaker
            game.toString() == "The Legend of Zelda: Twilight Princess" -> currentGameImage = R.drawable.twilightprincess
            game.toString() == "The Legend of Zelda: Oracle of Seasons" -> currentGameImage = R.drawable.oracleofseasons
            game.toString() == "The Legend of Zelda: Spirit Tracks" -> currentGameImage = R.drawable.spirittracks
            game.toString() == "BS The Legend of Zelda: Ancient Stone Tablets" -> currentGameImage = R.drawable.ancientstonetablets
            game.toString() == "Hyrule Warriors" -> currentGameImage = R.drawable.hyrulewarriors
            game.toString() == "The Legend of Zelda: Four Swords Adventures" -> currentGameImage = R.drawable.fourswordsadventures
            game.toString() == "The Legend of Zelda: Breath of the Wild" -> currentGameImage = R.drawable.breathofthewild
            game.toString() == "The Legend of Zelda: Tri Force Heroes" -> currentGameImage = R.drawable.triforceheroes
            game.toString() == "Zelda: The Wand of Gamelon" -> currentGameImage = R.drawable.wandofgamelon
            game.toString() == "The Legend of Zelda: Four Swords" -> currentGameImage = R.drawable.fourswords
            game.toString() == "The Legend of Zelda: The Minish Cap" -> currentGameImage = R.drawable.theminishcap
            game.toString() == "The Legend of Zelda: Four Swords Anniversary Edition" -> currentGameImage = R.drawable.fourswordsanniversary
            game.toString() == "Hyrule Warriors: Age of Calamity" -> currentGameImage = R.drawable.ageofcalamity
            game.toString() == "Link: The Faces of Evil" -> currentGameImage = R.drawable.thefacesofevil
            game.toString() == "The Legend of Zelda: Phantom Hourglass" -> currentGameImage = R.drawable.phantomhourglass
            game.toString() == "The Legend of Zelda: Link's Awakening" -> currentGameImage = R.drawable.linksawakening
            game.toString() == "The Legend of Zelda: A Link Between Worlds" -> currentGameImage = R.drawable.alinkbetweenworlds
            game.toString() == "The Legend of Zelda: Skyward Sword" -> currentGameImage = R.drawable.skywardsword
            game.toString() == "BS The Legend of Zelda" -> currentGameImage = R.drawable.bsthelegendofzelda
            game.toString() == "Freshly-Picked Tingle's Rosy Rupeeland" -> currentGameImage = R.drawable.tinglerosyrupeeland
            game.toString() == "Zelda's Adventure" -> currentGameImage = R.drawable.zeldasadventure
            game.toString() == "Hyrule Warriors Legends" -> currentGameImage = R.drawable.hyrulewarriorslegends
            game.toString() == "Link's Crossbow Training" -> currentGameImage = R.drawable.crossbowtraining
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showGame(view: View) {
        val gameDialog = GameDialog(game!!, currentGameImage)
        gameDialog.show(supportFragmentManager, "game dialog")
    }
}