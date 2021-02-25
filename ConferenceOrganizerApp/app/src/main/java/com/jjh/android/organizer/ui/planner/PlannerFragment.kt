package com.jjh.android.organizer.ui.planner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jjh.android.organizer.R
import com.jjh.android.organizer.db.DefaultSchedulerProvider
import com.jjh.android.organizer.db.OrganizerRepository

import kotlinx.android.synthetic.main.fragment_planner.*

class PlannerFragment : Fragment() {

    companion object {
        private const val TAG = "PlannerFragment"
    }

    private val selectionViewModel by viewModels<PlannerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectionViewModel.repository = OrganizerRepository(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_planner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        addSessionButton.setOnClickListener {
            Log.d(TAG, "on click listener for addSessionButton")
            SessionInputDialog(activity, object : SessionInputDialog.SessionIdDialogListener {
                override fun onOK(sessionId: Int) {
                    Log.d(TAG, "The user tapped OK, input is $sessionId")
                    selectionViewModel
                        .addSession(sessionId)
                        .observeOn(DefaultSchedulerProvider.ui())
                        .subscribe{
                            val list = selectionViewModel.sessions.map{
                                "Session${it.id} ${it.title} by ${it.presenter} " +
                                        "track${it.trackId} ${it.startTime} to ${it.endTime}"
                            }.toList()
                            val arrayAdapter: ArrayAdapter<String> =
                                ArrayAdapter<String>(
                                    requireContext(),
                                    R.layout.selection_listview_layout,
                                    R.id.selectedSessionTextView,
                                    list)
                            // Update the list view with friends
                            listView.adapter = arrayAdapter
                        }
                }

                override fun onCancel() {
                    Log.d(TAG, "The user tapped Cancel")
                }
            }).show()
        }


    }

}