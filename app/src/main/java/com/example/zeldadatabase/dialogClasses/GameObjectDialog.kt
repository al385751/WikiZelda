package com.example.zeldadatabase.dialogClasses

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.zeldadatabase.R
import com.example.zeldadatabase.additionalClasses.GameObject

class GameObjectDialog(_gameObject: GameObject) : AppCompatDialogFragment() {
    var gameObject = _gameObject

    lateinit var gameObjectTitleTextView: TextView
    lateinit var gameObjectDescriptionTextView: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.gameobject_dialog, null)

        gameObjectTitleTextView = view.findViewById(R.id.gameObjectTitleTextView)
        gameObjectDescriptionTextView = view.findViewById(R.id.gameObjectDescriptionTextView)

        gameObjectTitleTextView.text = gameObject.title
        gameObjectDescriptionTextView.text = gameObject.descr

        builder.setView(view)
                .setPositiveButton("CLOSE") { dialog, which ->
                    dialog.cancel()
                }

        return builder.create()
    }
}