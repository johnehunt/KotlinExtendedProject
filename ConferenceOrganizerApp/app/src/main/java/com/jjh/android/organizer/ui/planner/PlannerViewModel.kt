package com.jjh.android.organizer.ui.planner

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jjh.android.organizer.db.OrganizerRepository
import com.jjh.android.organizer.model.Session
import io.reactivex.rxjava3.core.Observable

class PlannerViewModel : ViewModel() {

    companion object {
        private const val TAG = "PlannerViewModel"
    }

    init {
        Log.d(TAG, "init{}")
    }

    lateinit var repository: OrganizerRepository

    val sessions: MutableList<Session> = mutableListOf()

    fun addSession(sessionId: Int): Observable<Session> {
        Log.d(TAG, "addSession($sessionId)")
        return repository.findSessionById(sessionId).doOnNext {
            sessions.add(it)

        }
    }



}