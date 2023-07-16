package com.tyrnor.littlelemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel(){

    private val _firstName = MutableLiveData<String>()
    val firstName : LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> = _lastName

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    fun onPersonalInformationChange( firstName: String, lastName: String, email: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _email.value = email
    }
}