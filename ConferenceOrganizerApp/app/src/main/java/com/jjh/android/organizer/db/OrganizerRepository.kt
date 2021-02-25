package com.jjh.android.organizer.db

import android.app.Application
import android.util.Log
import com.jjh.android.organizer.model.Session
import com.jjh.android.organizer.model.Track
import com.jjh.android.organizer.model.TrackWithSessions
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

class OrganizerRepository(application: Application,
                          private val schedulerProvider: SchedulerProvider = DefaultSchedulerProvider) {

    companion object {
        private const val TAG = "OrganizerRepository"
    }

    init {
        Log.d(TAG, "init{}")
    }

    private val sessionDao: SessionDao =
        OrganizerRoomDatabase.getDatabase(application).sessionDao()

    private val trackDao: TrackDao =
        OrganizerRoomDatabase.getDatabase(application).trackDao()

    private val tracksWithSessionsDao: TracksWithSessionsDao =
        OrganizerRoomDatabase.getDatabase(application).tracksWithSessionsDao()

    fun saveAllTracksAndSessions(tracks: List<Track>, sessions: List<Session>): Observable<List<Long>> {
        return Observable.create<List<Long>> {
            var result = trackDao.insertAll(*tracks.toTypedArray())
            result = result +  sessionDao.insertAll(*sessions.toTypedArray())
            it.onNext(result)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun findAllTracksWithSessions(): Observable<List<TrackWithSessions>> {
        return Observable.create<List<TrackWithSessions>> {
            val results = tracksWithSessionsDao.getTracksWithSessions()
            Log.d(TAG, results.toString())
            it.onNext(results)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun getTrackRowCount(): Observable<Int> {
        return Observable.create<Int> {
            val result = trackDao.getRowCount()
            it.onNext(result)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())
    }

    fun findSessionById(sessionId: Int): Observable<Session>  {
        return Observable.create<Session> {
            val session = sessionDao.findById(sessionId)
            it.onNext(session)
            it.onComplete()
        }.subscribeOn(schedulerProvider.newThread())

    }

}