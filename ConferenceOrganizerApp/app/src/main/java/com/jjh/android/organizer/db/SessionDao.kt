package com.jjh.android.organizer.db

import androidx.room.*
import com.jjh.android.organizer.model.Session

@Dao
interface SessionDao {

    @Insert
    fun insert(session: Session): Long

    @Insert
    fun insertAll(vararg sessions: Session): List<Long>

    @Update
    fun update(session: Session): Int

    @Query("SELECT * FROM sessions")
    fun findAll(): List<Session>

    @Query("SELECT * FROM sessions WHERE id=:id")
    fun findById(id: Int): Session

    @Delete
    fun delete(session: Session): Int

    @Query("DELETE FROM sessions WHERE id = :id")
    fun deleteById(id: Int): Int

    @Delete
    fun deleteAll(vararg sessions: Session): Int

    @Query("DELETE FROM sessions")
    fun deleteAll(): Int

    @Query("SELECT COUNT(id) FROM sessions")
    fun getRowCount(): Int

}