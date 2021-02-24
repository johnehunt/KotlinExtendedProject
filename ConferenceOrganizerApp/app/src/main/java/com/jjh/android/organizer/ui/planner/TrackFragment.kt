package com.jjh.android.organizer.ui.planner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jjh.android.organizer.R
import com.jjh.android.organizer.db.OrganizerRepository

import kotlinx.android.synthetic.main.fragment_planner.*

class TrackFragment : Fragment() {

    companion object {
        private const val TAG = "TrackFragment"
    }

    private val trackViewModel by viewModels<TrackViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_planner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        trackViewModel.repository = OrganizerRepository(requireActivity().application)
        trackViewModel.refresh().subscribe {
            if (it.isNotEmpty()) {
                Log.d(TAG, "setting up viewpager")
                viewPager.adapter = TabPagerAdapter(trackViewModel, parentFragmentManager)
                tabLayout.setupWithViewPager(viewPager)
            }
        }
    }

}