package com.tyrnor.littlelemon.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(): ViewModel() {

    //TODO: Implement all Live Data variables and methods
    private val _firstNameText = MutableLiveData<String>()
    val firstNameText : LiveData<String> = _firstNameText

    private val _lastNameText = MutableLiveData<String>()
    val lastNameText : LiveData<String> = _lastNameText

    private val _emailText = MutableLiveData<String>()
    val emailText : LiveData<String> = _emailText

    private val _firstNameError = MutableLiveData<Boolean>()
    val firstNameError : LiveData<Boolean> = _firstNameError

    private val _lastNameError = MutableLiveData<Boolean>()
    val lastNameError : LiveData<Boolean> = _lastNameError

    private val _emailError = MutableLiveData<Boolean>()
    val emailError : LiveData<Boolean> = _emailError


    fun onRegisterChanged( firstNameText: String, lastNameText: String, emailText: String) {
        _firstNameText.value = firstNameText
        _lastNameText.value = lastNameText
        _emailText.value = emailText
    }

    fun validEmail(emailText: String) = Patterns.EMAIL_ADDRESS.matcher(emailText).matches()


    fun onFirstNameError(firstNameError: Boolean) {
        _firstNameError.value = firstNameError
    }

    fun onLastNameError(lastNameError: Boolean) {
        _lastNameError.value = lastNameError
    }

    fun onEmailError(emailError: Boolean) {
        _emailError.value = emailError
    }
}