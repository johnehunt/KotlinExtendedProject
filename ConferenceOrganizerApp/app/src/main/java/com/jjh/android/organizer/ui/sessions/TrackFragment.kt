package com.jjh.android.organizer.ui.sessions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jjh.android.organizer.R
import com.jjh.android.organizer.db.OrganizerRepository

import kotlinx.android.synthetic.main.fragment_sessions.*

class TrackFragment : Fragment() {

    companion object {
        private const val TAG = "SessionFragment"
    }

    private val sessionViewModel by viewModels<TrackViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sessions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        sessionViewModel.repository = OrganizerRepository(requireActivity().application)
        sessionViewModel.refresh().subscribe {
            Log.d(TAG, "sessionview model refreshed")
            viewPager.adapter = TabPagerAdapter(sessionViewModel, parentFragmentManager)
            tabLayout.setupWithViewPager(viewPager)
        }
    }

}