package com.jjh.android.organizer.ui.conference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjh.android.organizer.R
import com.jjh.android.organizer.model.TrackWithSessions

class TrackTabFragment(private val trackWithSessions: TrackWithSessions): Fragment() {

    companion object {
        private const val TAG = "TrackTabFragment"
    }

    init {
        Log.d(TAG, "init{}")
    }

    private lateinit var trackRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        val view =  inflater.inflate(R.layout.tab_fragment, container, false)
        trackRecyclerView = view.findViewById(R.id.trackRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")

        trackRecyclerView.layoutManager = LinearLayoutManager(context)
        Log.d(TAG, "onViewCreated - sessions: ${trackWithSessions.sessions.toString()}")
        trackRecyclerView.adapter = SessionRowAdapter(requireActivity(),
                                                      trackWithSessions.sessions)

    }

}