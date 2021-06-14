package com.example.zeldadatabase.additionalClasses.inheritFromGameObject

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.GameObject

@Entity(primaryKeys = ["name", "gameId"],
        foreignKeys = [ForeignKey(
                entity = Game::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("gameId")
        )],
        tableName = "Character",
        indices = [androidx.room.Index("gameId")])

class Character(val name: String, val description: String, val gameId: String) : GameObject(name, description, gameId) {
}