package com.jjh.android.organizer.ui.conference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjh.android.organizer.R
import com.jjh.android.organizer.model.TrackWithSessions
import kotlinx.android.synthetic.main.tab_fragment.*

class TrackTabFragment(private val trackWithSessions: TrackWithSessions): Fragment() {

    companion object {
        private const val TAG = "TrackTabFragment"
    }

    init {
        Log.d(TAG, "init{}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")

        trackRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            Log.d(TAG, "onViewCreated - sessions: ${trackWithSessions.sessions.toString()}")
            adapter = SessionRowAdapter(context, trackWithSessions.sessions)
        }

    }

}