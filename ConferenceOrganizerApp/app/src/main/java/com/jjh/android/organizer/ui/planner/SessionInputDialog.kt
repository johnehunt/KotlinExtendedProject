package com.jjh.android.organizer.ui.planner

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper

import com.jjh.android.organizer.R

class SessionInputDialog(private val activity: Activity?,
                 private val listener: SessionIdDialogListener) :
    AlertDialog.Builder(ContextThemeWrapper(activity, R.style.ThemeOverlay_AppCompat)) {

    interface SessionIdDialogListener {
        fun onOK(sessionId: Int)
        fun onCancel()
    }

    override fun create(): AlertDialog {
        val dialogLayout: View =
            LayoutInflater.from(activity).inflate(R.layout.session_input_dialog_layout, null)
        setView(dialogLayout)

        val sessionIdEditText = dialogLayout.findViewById<EditText>(R.id.sessionIdEditText)

        setPositiveButton("OK") {
                dialog, id -> listener.onOK(sessionIdEditText.text.toString().toInt())
        }
        setNegativeButton("Cancel") { dialog, id -> listener.onCancel() }
        return super.create()
    }

}