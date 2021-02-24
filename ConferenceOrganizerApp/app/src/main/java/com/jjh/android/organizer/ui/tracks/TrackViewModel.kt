package com.jjh.android.organizer.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrackViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tracks Fragment"
    }
    val text: LiveData<String> = _text
}