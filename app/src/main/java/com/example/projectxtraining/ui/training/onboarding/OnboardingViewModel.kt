package com.example.projectxtraining.ui.training.onboarding

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OnboardingViewModel : ViewModel() {

    private val _imagePreviousPosition = MutableLiveData<Int>()
    private val _imagePosition = MutableLiveData<Int>()

    /**
     * Pair: prev position, cur position
     */
    val positionChange = MediatorLiveData<Pair<Int?, Int?>>().apply {
        addSource(_imagePreviousPosition) {
            value = it to value?.second
        }
        addSource(_imagePosition) {
            value = value?.first to it
        }
    }.distinctUntilChanged()

    fun onImageSelected(position: Int) {
        _imagePosition.value = position
    }

    fun onImageUnselected(position: Int) {
        _imagePreviousPosition.value = position
    }
}