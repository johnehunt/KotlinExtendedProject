package com.jjh.android.organizer.ui.planner

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jjh.android.organizer.db.OrganizerRepositoryManager
import com.jjh.android.organizer.model.Session
import io.reactivex.rxjava3.core.Observable

class PlannerViewModel : ViewModel() {

    companion object {
        private const val TAG = "PlannerViewModel"
    }

    val sessions: MutableList<Session>
        get() = OrganizerRepositoryManager.plannedSessions

    fun addSession(sessionId: Int): Observable<Session> {
        Log.d(TAG, "addSession($sessionId)")
        return OrganizerRepositoryManager.repository.findSessionById(sessionId).doOnNext {
            OrganizerRepositoryManager.plannedSessions.add(it)
        }
    }

}