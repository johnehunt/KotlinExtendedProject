package com.jjh.android.organizer.ui.sessions

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jjh.android.organizer.db.DefaultSchedulerProvider
import com.jjh.android.organizer.db.OrganizerRepository
import com.jjh.android.organizer.db.TracksWithSessionsDao
import com.jjh.android.organizer.model.TrackWithSessions
import io.reactivex.rxjava3.core.Observable

class TrackViewModel : ViewModel() {

    companion object {
        private const val TAG = "SessionViewModel"
    }

    lateinit var repository: OrganizerRepository

    var tracksWithSessions: List<TrackWithSessions> = listOf<TrackWithSessions>()

    fun refresh(): Observable<List<TrackWithSessions>> {
        Log.d(TAG, "refresh()")
        return repository.findAllTracksWithSessions()
            .observeOn(DefaultSchedulerProvider.ui())
            .doOnNext {
                tracksWithSessions = it
            }
    }

    val size
        get() = tracksWithSessions.size

    fun get(position: Int): TrackWithSessions {
        return tracksWithSessions[position]
    }

    fun getTrack(position: Int) = get(position).track

    fun getTrackName(position: Int): String = get(position).track.name

    fun getTrackSessions(position: Int) = get(position).sessions


}