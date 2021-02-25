package com.jjh.android.organizer.ui.conference

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jjh.android.organizer.db.DefaultSchedulerProvider
import com.jjh.android.organizer.db.OrganizerRepository
import com.jjh.android.organizer.db.OrganizerRepositoryManager
import com.jjh.android.organizer.model.TrackWithSessions
import io.reactivex.rxjava3.core.Observable

class ConferenceViewModel : ViewModel() {

    companion object {
        private const val TAG = "ConferenceViewModel"
    }

    val tracksWithSessions: MutableList<TrackWithSessions>
            get() = OrganizerRepositoryManager.tracksWithSessions

    val size
        get() = OrganizerRepositoryManager.tracksWithSessions.size

    fun get(position: Int): TrackWithSessions {
        Log.d(TAG, "get($position)")
        return OrganizerRepositoryManager.tracksWithSessions[position]
    }

    fun getTrackName(position: Int): String = get(position).track.name

    fun getTrackRoom(position: Int): String = get(position).track.room

}