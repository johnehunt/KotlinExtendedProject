package com.jjh.android.organizer.db

import android.util.Log
import android.widget.Toast
import com.jjh.android.organizer.MainActivity
import com.jjh.android.organizer.model.Session
import com.jjh.android.organizer.model.Track
import com.jjh.android.organizer.model.TrackWithSessions
import com.jjh.android.organizer.ui.conference.ConferenceViewModel
import io.reactivex.rxjava3.core.Observable

object OrganizerRepositoryManager{

    private const val TAG = "OrganizerRepositoryManager"

    lateinit var repository: OrganizerRepository

    val tracksWithSessions: MutableList<TrackWithSessions> = mutableListOf()

    val plannedSessions: MutableList<Session> = mutableListOf()

    fun setup(activity: MainActivity) {
        Log.d(TAG, "setup()")
        repository.getTrackRowCount()
            .observeOn(DefaultSchedulerProvider.ui())
            .subscribe {
                if (it == 0) {
                    // First time being run - need to initialise data
                    setupTracksAndSessions(activity)
                } else {
                    refresh().subscribe {
                        Toast.makeText(activity, "App Ready", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    fun refresh(): Observable<List<TrackWithSessions>> {
        Log.d(TAG, "refresh()")
        return repository!!.findAllTracksWithSessions()
            .observeOn(DefaultSchedulerProvider.ui())
            .doOnNext {
                Log.d(TAG, "refresh() - $it")
                tracksWithSessions.clear()
                tracksWithSessions.addAll(0,it)
            }
    }

    private fun setupTracksAndSessions(activity: MainActivity) {
        val tracks = listOf(
            Track(1, "Primary Track", "A12"),
            Track(2, "Secondary Track", "B4"),
            Track(3, "Tertiary Track", "C56")
        )

        val sessions = listOf(
            Session(1, "Welcome", "Welcome to the conference", "Adam Smith", 1, "09:00", "10:00"),
            Session(
                2,
                "Kotlin Introduction",
                "A brief introduction to the Kotlin language",
                "Jasmine Jones",
                1,
                "10:00",
                "11:00"
            ),
            Session(
                3,
                "Kotlin for All",
                "Why Kotlin is a good programming langauge for all",
                "Phoebe Bates",
                1,
                "11:00",
                "12:00"
            ),
            Session(
                4,
                "Kotlin on the Server",
                "Server Side Kotlin - an introduction",
                "Denise James",
                1,
                "13:00",
                "14:00"
            ),
            Session(
                5,
                "Kotlin for the Web",
                "Running Kotlin within a Web Browser",
                "Gryff Davies",
                1,
                "14:00",
                "15:00"
            ),
            Session(
                6,
                "Kotlin Advanced",
                "Looking at some of the advanced language features in Kotlin",
                "John Fosh",
                1,
                "15:00",
                "16:00"
            ),

            Session(
                7,
                "Android Intro",
                "A brief introduction to Android",
                "Andrew North",
                2,
                "09:00",
                "10:00"
            ),
            Session(
                8,
                "Android Activities",
                "Activities and their lifecycle",
                "Jeremy Prescott",
                2,
                "10:00",
                "11:00"
            ),
            Session(
                9,
                "Android Fragments",
                "Working with Modern Android application Fragments",
                "Gryff Davis",
                2,
                "11:00",
                "12:00"
            ),
            Session(
                10,
                "Android Services",
                "Utilizing Android Services in your app",
                "Phoebe Bates",
                2,
                "13:00",
                "14:00"
            ),
            Session(
                11,
                "Android Testing",
                "Test, Test and test again",
                "Darren Rose",
                2,
                "14:00",
                "15:00"
            ),
            Session(
                12,
                "Android ROOM",
                "Using ROOM in an Android app",
                "Paul Starmer",
                2,
                "15:00",
                "16:00"
            ),

            Session(
                13,
                "UI Basics",
                "The basic rules of UI design",
                "Darren Rose",
                3,
                "09:00",
                "10:00"
            ),
            Session(
                14,
                "UI Navigation",
                "Adding navigation components to an app",
                "Jeremy Prescott",
                3,
                "10:00",
                "11:00"
            ),
            Session(15, "UI Design", "Designing your UI", "Denise James", 3, "11:00", "12:00"),
            Session(
                16,
                "UI Advanced",
                "Building advanced UIs",
                "Jasmine Jones",
                3,
                "13:00",
                "14:00"
            ),
            Session(17, "UI Testing", "How to test a UI", "Bill Smith", 3, "14:00", "15:00"),
            Session(
                18,
                "The UI Future",
                "The future of Android UIs",
                "Gryff Davis",
                3,
                "15:00",
                "16:00"
            )

        )

        repository
            .saveAllTracksAndSessions(tracks, sessions)
            .observeOn(DefaultSchedulerProvider.ui())
            .subscribe{
                refresh().subscribe {
                    Toast.makeText(activity, "App Data Initialised - Ready", Toast.LENGTH_LONG)
                        .show()
                }
            }

    }

}