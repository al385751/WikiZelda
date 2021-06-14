package com.example.zeldadatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*
import com.example.zeldadatabase.modelStuff.SingletonHolder

@Database(
    entities = [Game::class,
                Character::class,
                Dungeon::class,
                FinalBoss::class,
                Item::class,
                Monster::class,
                Place::class],
    version = 1,
    exportSchema = false
)

abstract class AbstractDatabase : RoomDatabase() {
    abstract fun getDAO() : DAO
}

class MyDatabase private constructor(context: Context) {
    companion object: SingletonHolder<MyDatabase, Context>(::MyDatabase)

    val dao = databaseBuilder(context, AbstractDatabase::class.java, "ZeldaDatabase")
        .build()
        .getDAO()
}