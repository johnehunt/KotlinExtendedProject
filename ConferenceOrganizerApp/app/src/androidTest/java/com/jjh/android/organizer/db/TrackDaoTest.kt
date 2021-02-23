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
    fun addTrackTest() {
        val track = Track(1, "Primary Track")
        trackDao!!.insert(track)
        val foundTrack = trackDao?.findById(1)
        foundTrack?.apply {
            Assert.assertEquals(1, this.id)
            Assert.assertEquals("Primary Track", this.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun addAllTracksTest() {
        val track2 = Track(2, "Secondary Track")
        val track3 = Track(3, "Tertiary Track")

        trackDao!!.insertAll(track2, track3)

        val foundTracks = trackDao?.findAll()
        foundTracks?.run{
            Assert.assertEquals(3, size)
        }
    }
    
}