package com.jjh.android.organizer.db

import android.app.Application
import android.util.Log
import com.jjh.android.organizer.model.Session
import com.jjh.android.organizer.model.Track
import com.jjh.android.organizer.model.TrackWithSessions
import io.reactivex.rxjava3.core.Observable

class OrganizerRepository(private val application: Application,
                     private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider) {

    companion object {
        private const val TAG = "HeroRepository"
    }

    private val sessionDao: SessionDao =
        OrganizerRoomDatabase.getDatabase(application).sessionDao()

    private val trackDao: TrackDao =
        OrganizerRoomDatabase.getDatabase(application).trackDao()

    private val tracksWithSessionsDao: TracksWithSessionsDao =
        OrganizerRoomDatabase.getDatabase(application).tracksWithSessionsDao()


    fun close() {
        Log.d(TAG, "close()")
        OrganizerRoomDatabase.getDatabase(application).close()
    }

    fun findAllSessions(): Observable<List<Session>> {
        return Observable.create<List<Session>> {
            val results = sessionDao.findAll()
            it.onNext(results)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun findAllTracks(): Observable<List<Track>> {
        return Observable.create<List<Track>> {
            val results = trackDao.findAll()
            it.onNext(results)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun findAllTracksWithSessions(): Observable<List<TrackWithSessions>> {
        return Observable.create<List<TrackWithSessions>> {
            val results = tracksWithSessionsDao.getTracksWithSessions()
            it.onNext(results)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }




}