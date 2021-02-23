package com.jjh.android.organizer.db

import androidx.room.*

import com.jjh.android.organizer.model.TrackWithSessions

@Dao
interface TracksWithSessionsDao {

    @Transaction
    @Query("SELECT * FROM tracks")
    fun getTracksWithSessions(): List<TrackWithSessions>

}
