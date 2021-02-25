package com.jjh.android.organizer.ui.conference

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter


class TrackTabPagerAdapter(
    private val conferenceViewModel: ConferenceViewModel,
    fragment: Fragment): FragmentStateAdapter(fragment) {

    companion object {
        private const val TAG = "TrackTabPagerAdapter"
    }

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "getItem($position)")
        val trackWithSession = conferenceViewModel.tracksWithSessions[position]
        return TrackTabFragment(trackWithSession)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getCount() - ${conferenceViewModel.size}")
        return conferenceViewModel.size
    }

}