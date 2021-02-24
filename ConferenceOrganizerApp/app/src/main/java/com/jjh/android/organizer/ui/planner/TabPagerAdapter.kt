package com.jjh.android.organizer.ui.planner

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(private val trackViewModel: TrackViewModel,
                      fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val TAG = "TabPagerAdapter"
    }

    private var tabFragments: MutableList<TabFragment> = mutableListOf()

    init {
        Log.d(TAG, "<init>")
        val tracksWithSessions = trackViewModel.tracksWithSessions
        tracksWithSessions.forEach{
            val tabFragment = TabFragment(it)
            tabFragments.add(tabFragment)
        }
    }

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem($position)")
        return tabFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.d(TAG, "getPageTitle($position)")
        return "${trackViewModel.getTrackName(position)} - ${trackViewModel.getTrackRoom(position)}"
    }

    override fun getCount(): Int {
        Log.d(TAG, "getCount() - ${trackViewModel.size}")
        return trackViewModel.size
    }
}