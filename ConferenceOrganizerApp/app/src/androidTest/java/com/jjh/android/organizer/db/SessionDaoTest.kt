package com.jjh.android.organizer.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jjh.android.organizer.model.Session
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SessionDaoTest {

    companion object {
        private const val TAG = "SessionDaoTest"
    }

    private var db: OrganizerRoomDatabase?
    private var sessionDao: SessionDao?

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = OrganizerRoomDatabase.getDatabase(context)
        sessionDao = db?.sessionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db?.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAllSessionsTest() {
        val session1 = Session(1, "Welcome", "Welcome to the conference", "Adam Smith", 1, "09:00", "10:00")
        val session2 = Session(2, "Kotlin Introduction", "A brief introduction to the Kotlin language", "Jasmine Jones", 1, "10:00", "11:00")
        val session3 = Session(3, "Kotlin for All", "Why Kotlin is a good programming langauge for all", "Phoebe Bates", 1, "11:00", "12:00")
        val session4 = Session(4, "Kotlin on the Server", "Server Side Kotlin - an introduction", "Denise James", 1, "13:00", "14:00")
        val session5 = Session(5, "Kotlin for the Web", "Running Kotlin within a Web Browser", "Gryff Davies", 1, "14:00", "15:00")
        val session6 = Session(6, "Kotlin Advanced", "Looking at some of the advanced language features in Kotlin", "John Fosh", 1, "15:00", "16:00")

        val session7 = Session(7, "Android Intro", "A brief introduction to Android", "Andrew North", 2, "09:00", "10:00")
        val session8 = Session(8, "Android Activities", "Activities and their lifecycle", "Jeremy Prescott", 2, "10:00", "11:00")
        val session9 = Session(9, "Android Fragments", "Working with Modern Android application Fragments", "Gryff Davis", 2, "11:00", "12:00")
        val session10 = Session(10, "Android Services", "Utilizing Android Services in your app", "Phoebe Bates", 2, "13:00", "14:00")
        val session11 = Session(11, "Android Testing", "Test, Test and test again", "Darren Rose", 2, "14:00", "15:00")
        val session12 = Session(12, "Android ROOM", "Using ROOM in an Android app", "Paul Starmer", 2, "15:00", "16:00")

        val session13 = Session(13, "UI Basics", "The basic rules of UI design", "Darren Rose", 3, "09:00", "10:00")
        val session14 = Session(14, "UI Navigation", "Adding navigation components to an app", "Jeremy Prescott", 3, "10:00", "11:00")
        val session15 = Session(15, "UI Design", "Designing your UI", "Denise James", 3, "11:00", "12:00")
        val session16 = Session(16, "UI Advanced", "Building advanced UIs", "Jasmine Jones", 3, "13:00", "14:00")
        val session17 = Session(17, "UI Testing", "How to test a UI", "Bill Smith", 3, "14:00", "15:00")
        val session18 = Session(18, "The UI Future", "The future of Android UIs", "Gryff Davis", 3, "15:00", "16:00")

        sessionDao!!.insertAll(session1, session2, session3, session4, session5, session6,
        session7, session8, session9, session10, session11, session12,
        session13, session14, session15, session16, session17, session18)

        val foundSessions = sessionDao?.findAll()
        foundSessions?.run{
            Assert.assertEquals(18, size)
        }
    }

    @Test
    @Throws(Exception::class)
    fun findSessionByIDTest() {
        val foundSession = sessionDao?.findById(1)
        foundSession?.apply {
            Assert.assertEquals(1, this.id)
            Assert.assertEquals("Welcome", this.title)
            Assert.assertEquals("Welcome to the conference", this.description)
            Assert.assertEquals("Adam Smith", this.presenter)
            Assert.assertEquals(1, this.trackId)
            Assert.assertEquals("09:00", this.startTime)
            Assert.assertEquals("10:00", this.endTime)
        }
    }

    @Test
    fun deleteTracksTest() {
        sessionDao!!.deleteAll()
    }

    @Test
    fun findAllSessionsTest() {
        val foundSessions = sessionDao?.findAll()
        foundSessions?.run{
            Assert.assertEquals(18, size)
        }
    }

    @Test
    fun getRowCountTest() {
        val result = sessionDao!!.getRowCount()
        Assert.assertEquals(0, result)
    }


}