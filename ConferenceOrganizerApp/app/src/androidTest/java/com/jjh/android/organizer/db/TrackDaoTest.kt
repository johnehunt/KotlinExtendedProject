package com.jjh.android.organizer.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jjh.android.organizer.model.Track
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TrackDaoTest {

    companion object {
        private const val TAG = "TrackDaoTest"
    }

    private var db: OrganizerRoomDatabase?
    private var trackDao: TrackDao?

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = OrganizerRoomDatabase.getDatabase(context)
        trackDao = db?.trackDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAllTracksTest() {
        val track1 = Track(1, "Primary Track", "A12")
        val track2 = Track(2, "Secondary Track", "B4")
        val track3 = Track(3, "Tertiary Track", "C56")

        trackDao!!.insertAll(track1, track2, track3)

        val foundTracks = trackDao?.findAll()
        foundTracks?.run{
            Assert.assertEquals(3, size)
        }
    }

    @Test
    fun getRowCountTest() {
        val result = trackDao!!.getRowCount()
        Assert.assertEquals(0, result)
    }

    fun deleteAllTest() {
        trackDao?.deleteAll()
    }

    fun deleteTrack() {
        trackDao?.deleteById(1)
    }

    fun deleteTracks() {
        val track1 = Track(1, "Primary Track", "A12")
        val track2 = Track(2, "Secondary Track", "B4")
        val track3 = Track(3, "Tertiary Track", "C56")
        trackDao?.deleteAll(track1, track2, track3)
    }
    
}