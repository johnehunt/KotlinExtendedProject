package com.jjh.android.organizer.ui.conference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.jjh.android.organizer.databinding.FragmentConferenceBinding


class ConferenceFragment : Fragment() {

  companion object {
    private const val TAG = "ConferenceFragment"
  }

  private var _binding: FragmentConferenceBinding? = null
  private val binding get() = _binding!!

  private val viewModel by viewModels<ConferenceViewModel>()

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    Log.d(TAG, "onCreateView")
    _binding = FragmentConferenceBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d(TAG, "onViewCreated")
    binding.conferenceViewPager2.adapter = TrackTabPagerAdapter(viewModel, this)

    val mediator = TabLayoutMediator(binding.tabLayout, binding.conferenceViewPager2)
      { tab, position ->
        tab.text = "${viewModel.getTrackName(position)} - ${viewModel.getTrackRoom(position)}"
      }
    mediator.attach()

  }

}
