package com.jjh.android.organizer.db

import androidx.room.*
import com.jjh.android.organizer.model.Track

@Dao
interface TrackDao {

    @Insert
    fun insert(track: Track): Long

    @Insert
    fun insertAll(vararg tracks: Track): List<Long>

    @Update
    fun update(track: Track): Int

    @Query("SELECT * FROM tracks")
    fun findAll(): List<Track>

    @Query("SELECT * FROM tracks WHERE id=:id")
    fun findById(id: Int): Track

    @Delete
    fun delete(track: Track): Int

    @Query("DELETE FROM tracks WHERE id = :id")
    fun deleteById(id: Int): Int

    @Delete
    fun deleteAll(vararg tracks: Track): Int

}