package com.jjh.android.organizer.db

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TracksWithSessionsDaoTest {

    companion object {
        private const val TAG = "FriendDAOTest"
    }

    private var db: OrganizerRoomDatabase?
    private var tracksWithSessionsDao: TracksWithSessionsDao?

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = OrganizerRoomDatabase.getDatabase(context)
        tracksWithSessionsDao = db?.tracksWithSessionsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }

    @Test
    @Throws(Exception::class)
    fun findTracksWithSessionsTest() {
        val tracksWithSessions = tracksWithSessionsDao?.getTracksWithSessions()
        Log.d(TAG, "findAlltracksTest - $tracksWithSessions")
        assertEquals(3, tracksWithSessions?.size)
    }


}