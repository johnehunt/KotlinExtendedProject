package com.jjh.android.organizer.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjh.android.organizer.R

class SessionFragment : Fragment() {

    private lateinit var trackViewModel: SessionViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        trackViewModel =
                ViewModelProvider(this).get(SessionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tracks, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        trackViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}