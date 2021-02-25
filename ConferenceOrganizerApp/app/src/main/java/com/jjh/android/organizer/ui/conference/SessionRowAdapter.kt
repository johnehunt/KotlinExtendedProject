package com.jjh.android.organizer.ui.conference

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjh.android.organizer.model.Session

/**
 * Provide binding from the hero list to HeroViewHolders displayed
 * within a {@link RecyclerView}.
 */
class SessionRowAdapter(val context: Context,
                        private val sessions: List<Session>) : RecyclerView.Adapter<SessionRowViewHolder>() {

    companion object {
        private const val TAG = "SessionRowAdapter"
    }

    /**
     * Called when RecyclerView needs a new {@link HeroViewHolder} to represent
     * a Hero.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionRowViewHolder {
        Log.d(TAG, "onCreateViewHolder()")
        val inflater = LayoutInflater.from(parent.context)
        return SessionRowViewHolder(inflater, parent)
    }

    /**
     * Called by RecyclerView to display the HeroViewHolder at the specified position.
     */
    override fun onBindViewHolder(holderRow: SessionRowViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder($position)")
        sessions[position].run {
            holderRow.bind(this)
        }
    }

    /**
     *
     * Returns the total number of Heroes in the data set held by the view model.
     */
    override fun getItemCount(): Int = sessions.size

}

