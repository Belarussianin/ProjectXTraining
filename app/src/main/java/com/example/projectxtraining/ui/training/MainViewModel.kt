package com.example.projectxtraining.ui.training

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged

class MainViewModel : ViewModel() {

    private val _imagePreviousPosition = MutableLiveData<Int>()
    private val _imagePosition = MutableLiveData<Int>()

    /**
     * Pair: prev position, cur position
     */
    val positionChange = MediatorLiveData<Pair<Int, Int>>().apply {
        addSource(_imagePreviousPosition) {
            value = Pair(it, value?.second ?: it)
        }
        addSource(_imagePosition) {
            value = Pair(value?.first ?: it, it)
        }
    }.distinctUntilChanged()

    fun onImageSelected(position: Int) {
        _imagePosition.value = position
    }

    fun onImageUnselected(position: Int) {
        _imagePreviousPosition.value = position
    }
}