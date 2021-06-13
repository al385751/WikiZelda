package com.example.zeldadatabase.dialogClasses

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.Game

class GameDialog(_game: Game, _image: Int) : AppCompatDialogFragment() {
    var thisGame = _game
    var image = _image

    lateinit var titleGameTextView : TextView
    lateinit var releaseDateTextView: TextView
    lateinit var gameImageView: ImageView
    lateinit var descriptionGameTextView: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.game_dialog, null)

        titleGameTextView = view.findViewById(R.id.bigGameTitleTextView)
        releaseDateTextView = view.findViewById(R.id.bigReleaseDateTextView)
        gameImageView = view.findViewById(R.id.bigGameImageView)
        descriptionGameTextView = view.findViewById(R.id.gameDescriptionTextView)

        titleGameTextView.text = thisGame.title
        releaseDateTextView.text = thisGame.released
        gameImageView.setImageResource(image)
        descriptionGameTextView.text = thisGame.description

        builder.setView(view)
                .setPositiveButton("Done") { dialog, which ->
                    dialog.cancel()
                }

        return builder.create()
    }
}