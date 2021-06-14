package com.example.zeldadatabase.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zeldadatabase.additionalClasses.Game
import com.example.zeldadatabase.additionalClasses.inheritFromGameObject.*

@Dao
interface DAO {

    @Query ("SELECT * FROM Game")
    fun getGames() : List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(games: List<Game>)

    @Query("SELECT * FROM Character WHERE gameId = :game")
    fun getCharacters(game: String) : List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Query("SELECT * FROM Dungeon WHERE gameId = :game")
    fun getDungeons(game: String) : List<Dungeon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDungeons(dungeons: List<Dungeon>)

    @Query("SELECT * FROM FinalBoss WHERE gameId = :game")
    fun getBosses(game: String) : List<FinalBoss>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBosses(bosses: List<FinalBoss>)

    @Query("SELECT * FROM Item WHERE gameId = :game")
    fun getItems(game: String) : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<Item>)

    @Query("SELECT * FROM Monster WHERE gameId = :game")
    fun getMonsters(game: String) : List<Monster>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMonsters(monsters: List<Monster>)

    @Query("SELECT * FROM Place WHERE gameId = :game")
    fun getPlaces(game: String) : List<Place>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaces(places: List<Place>)
}