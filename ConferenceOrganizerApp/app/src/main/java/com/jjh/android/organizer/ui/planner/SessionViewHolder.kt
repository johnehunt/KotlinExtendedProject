package com.jjh.android.organizer.ui.planner

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjh.android.organizer.R
import com.jjh.android.organizer.model.Session

class SessionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.session_list_layout, parent, false)) {

    companion object {
        private const val TAG = "SessionViewHolder"
    }

    private val sessionId = itemView.findViewById<TextView>(R.id.sessionId)
    private val sessionTitle = itemView.findViewById<TextView>(R.id.sessionTitle)
    private val sessionDescription = itemView.findViewById<TextView>(R.id.sessionDescription)
    private val sessionPresenter = itemView.findViewById<TextView>(R.id.sessionPresenter)
    private val sessionStartTime = itemView.findViewById<TextView>(R.id.sessionStartTime)
    private val sessionEndTime = itemView.findViewById<TextView>(R.id.sessionEndTime)

    fun bind(session: Session) {
        Log.d(TAG, "bind($session)")
        sessionId.text = session.id.toString()
        sessionTitle.text = session.title
        sessionDescription.text = session.description
        sessionPresenter.text = session.presenter
        sessionStartTime.text = session.startTime
        sessionEndTime.text = session.endTime
    }

}