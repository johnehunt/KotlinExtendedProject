package com.jjh.android.organizer.ui.sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Session Fragment"
    }
    val text: LiveData<String> = _text
}