package com.jjh.android.organizer.ui.conference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jjh.android.organizer.R

import kotlinx.android.synthetic.main.fragment_conference.*

class ConferenceFragment : Fragment() {

    companion object {
        private const val TAG = "ConferenceFragment"
    }

    private val viewModel by viewModels<ConferenceViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_conference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        conferenceViewPager.adapter = TrackTabPagerAdapter(viewModel, parentFragmentManager)
        tabLayout.setupWithViewPager(conferenceViewPager)
    }

}