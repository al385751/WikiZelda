package com.example.zeldadatabase.showDataActivity

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
import com.example.zeldadatabase.additionalClasses.*
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Character
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.FinalBoss
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Monster
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.Place
import com.example.zeldadatabase.dialogClasses.GameDialog
import com.example.zeldadatabase.dialogClasses.GameObjectDialog
import com.example.zeldadatabase.recyclerViewAdapters.ItemsAdapter
import com.example.zeldadatabase.modelStuff.Model

class ShowDataView : AppCompatActivity(), IShowDataView {

    lateinit var game : Game
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

        game = intent.getParcelableExtra("Game")!!

        characters = intent.getBooleanExtra("Characters", false)
        monsters = intent.getBooleanExtra("Monsters", false)
        bosses = intent.getBooleanExtra("FinalBosses", false)
        places = intent.getBooleanExtra("Places", false)
        items = intent.getBooleanExtra("Items", false)
        dungeons = intent.getBooleanExtra("Dungeons", false)

        progressBar = findViewById(R.id.progressBar2)
        rvItems = findViewById(R.id.itemDataRecyclerView)

        nameGameTextView = findViewById(R.id.nameGameTextView)
        gameImageView = findViewById(R.id.gameImageView)

        val model = Model(applicationContext)
        presenter = ShowDataPresenter(this, model)

        nameGameTextView.text = game.toString()

        if (characters) {
            presenter.getCharacters(game)
        }

        else if (monsters) {
            presenter.getMonsters(game)
        }

        else if (bosses) {
            presenter.getBosses(game)
        }

        else if (places) {
            presenter.getPlaces(game)
        }

        else if (items) {
            presenter.getItems(game)
        }

        else {
            presenter.getDungeons(game)
        }

        setCurrentImage()

        gameImageView.setImageResource(currentGameImage)

        title = "Showing Info"
    }

    override var progressBarVisible : Boolean
        get() = progressBar.visibility == View.VISIBLE
        set(value) {
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    override var recycleViewVisible: Boolean
        get() = rvItems.visibility == View.VISIBLE
        set(value) {
            rvItems.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun createRecyclerView(itemList: ArrayList<ItemFromRecycleView>) {
        val adapter = ItemsAdapter(itemList, this)
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