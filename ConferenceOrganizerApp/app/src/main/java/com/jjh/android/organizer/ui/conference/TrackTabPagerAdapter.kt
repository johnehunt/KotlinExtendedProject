package com.jjh.android.organizer.ui.conference

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TrackTabPagerAdapter(
    private val conferenceViewModel: ConferenceViewModel,
    fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val TAG = "TrackTabPagerAdapter"
    }

    private var trackTabFragments: MutableList<TrackTabFragment> = mutableListOf()

    init {
        Log.d(TAG, "<init>")
        conferenceViewModel.tracksWithSessions.forEach{
            Log.d(TAG, "<init> - ${it.toString()}")
            val tabFragment = TrackTabFragment(it)
            trackTabFragments.add(tabFragment)
        }
    }

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem($position)")
        return trackTabFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.d(TAG, "getPageTitle($position)")
        return "${conferenceViewModel.getTrackName(position)} - ${conferenceViewModel.getTrackRoom(
            position
        )}"
    }

    override fun getCount(): Int {
        Log.d(TAG, "getCount() - ${conferenceViewModel.size}")
        return conferenceViewModel.size
    }

}