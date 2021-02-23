package com.jjh.android.organizer.ui.sessions

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

class TabFragment(private val trackWithSessions: TrackWithSessions): Fragment() {

    companion object {
        private const val TAG = "TabFragment"
    }

    private lateinit var layout: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        layout =  inflater.inflate(R.layout.tab_fragment, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hero_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SessionAdapter(context, trackWithSessions.sessions)
        }
    }
}