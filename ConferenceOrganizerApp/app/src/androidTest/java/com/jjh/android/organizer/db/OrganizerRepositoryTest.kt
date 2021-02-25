package com.jjh.android.organizer.db

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class OrganizerRepositoryTest {

    companion object {
        private const val TAG = "FriendRepositoryTest"
    }

    private val repository: OrganizerRepository

    init {
        val application = ApplicationProvider.getApplicationContext<Context>() as Application
        repository = OrganizerRepository(application, TestSchedulerProvider)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        val application = ApplicationProvider.getApplicationContext<Context>() as Application
        OrganizerRoomDatabase.getDatabase(application).close()
    }

    @Test
    fun getTrackRowCountTest() {
        Log.d(TAG, "Setting up test Observer")
        val testObserver = TestObserver<Int>()

        repository.getTrackRowCount()
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(testObserver)
        Log.d(TAG, "checking test observer")
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val result = testObserver.values()[0]
        Assert.assertTrue("number of tracks", result == 0)
    }


}