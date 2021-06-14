package com.example.zeldadatabase.insertDataActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Display
import android.view.View
import android.widget.*
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.modelStuff.Model
import com.example.zeldadatabase.showDataActivity.ShowDataView

class MainActivity : AppCompatActivity(), IMainView {
    lateinit var autoCompleteZeldaGames : AutoCompleteTextView
    lateinit var characterCheckBox : CheckBox
    lateinit var monstersCheckBox: CheckBox
    lateinit var bossesCheckBox: CheckBox
    lateinit var placesCheckBox : CheckBox
    lateinit var itemsCheckBox : CheckBox
    lateinit var dungeonCheckBox : CheckBox
    lateinit var searchButton: Button

    lateinit var choseGameTextView: TextView
    lateinit var choseParametersTextView: TextView

    lateinit var progressBar: ProgressBar

    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteZeldaGames = findViewById(R.id.gameAutoComplete)
        characterCheckBox = findViewById(R.id.charactersCheckBox)
        monstersCheckBox = findViewById(R.id.monstersCheckBox)
        bossesCheckBox = findViewById(R.id.bossesCheckBox)
        placesCheckBox = findViewById(R.id.placesCheckBox)
        itemsCheckBox = findViewById(R.id.itemsCheckBox)
        dungeonCheckBox = findViewById(R.id.dungeonsCheckBox)
        searchButton = findViewById(R.id.searchButton)

        choseGameTextView = findViewById(R.id.choseGameTextView)
        choseParametersTextView = findViewById(R.id.choseParametersTextView)

        progressBar = findViewById(R.id.progressBar)

        val model = Model(applicationContext)
        presenter = Presenter(this, model)
    }

    override var progressBarVisible: Boolean
        get() = progressBar.visibility == View.VISIBLE
        set(value) {
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    override var everythingVisible: Boolean
        get() = choseGameTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            choseGameTextView.visibility = v
            autoCompleteZeldaGames.visibility = v
            choseParametersTextView.visibility = v
            characterCheckBox.visibility = v
            monstersCheckBox.visibility = v
            bossesCheckBox.visibility = v
            placesCheckBox.visibility = v
            itemsCheckBox.visibility = v
            dungeonCheckBox.visibility = v
            searchButton.visibility = v
        }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showGames(games: List<Game>) {
        val gamesName : ArrayList<String> = ArrayList()

        games.forEach {
            gamesName.add(it.title)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, gamesName)
        autoCompleteZeldaGames.apply {
            setAdapter(adapter)
            setText("")
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val game = s.toString()
                    games.binarySearch { it.title.compareTo(game) }.let {
                        if (it >= 0)
                            presenter.setChosenGame(games[it])
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

            })
        }
    }

    override fun searchInfo(view: View) {
        if (presenter.getGame() != null) {
            if (bossesCheckBox.isChecked || characterCheckBox.isChecked || dungeonCheckBox.isChecked ||
                    itemsCheckBox.isChecked || monstersCheckBox.isChecked || placesCheckBox.isChecked) {
                val intent = Intent(this, ShowDataView::class.java)
                intent.putExtra("Game", presenter.getGame())

                intent.putExtra("Characters", characterCheckBox.isChecked)
                intent.putExtra("Dungeons", dungeonCheckBox.isChecked)
                intent.putExtra("FinalBosses", bossesCheckBox.isChecked)
                intent.putExtra("Items", itemsCheckBox.isChecked)
                intent.putExtra("Monsters", monstersCheckBox.isChecked)
                intent.putExtra("Places", placesCheckBox.isChecked)

                startActivity(intent)
            }

            else showError("You have to select some parameters to search in the selected game")
        }

        else showError("You have to select a game in which you want to do the search")
    }
}