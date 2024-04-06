package com.stupa.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stupa.constants.Constants
import com.stupa.db.RegisterRepository
import com.stupa.util.SharedPreference
import com.stupa.util.isValidEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository,
                     private val sharedPreference: SharedPreference, application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val inputUsername = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails



    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    private val _errorToastUsernameInvalid = MutableLiveData<Boolean>()

    val errorToastUsernameInvalid: LiveData<Boolean>
        get() = _errorToastUsernameInvalid

    private val _errorToastUsernameNotExit = MutableLiveData<Boolean>()

    val errorToastUsernameNotExit: LiveData<Boolean>
        get() = _errorToastUsernameNotExit


    private val _errorToastPassword = MutableLiveData<Boolean>()

    val errorToastPassword: LiveData<Boolean>
        get() = _errorToastPassword

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun signUP() {
        _navigatetoRegister.value = true
    }

    fun loginButton() {
        if (inputUsername.value == null) {
            _errorToastUsername.value = true
        } else if (!inputUsername.value!!.isValidEmail()) {
            _errorToastUsernameInvalid.value = true
        } else if (inputPassword.value == null) {
            _errorToastPassword.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    if (usersNames.password == inputPassword.value) {
                        inputUsername.value = null
                        inputPassword.value = null

                        _navigatetoUserDetails.value = true
                        sharedPreference.putString(Constants.USER_NAME,usersNames.email)
                    } else {
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUsernameNotExit.value = true
                }
            }
        }
    }


    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }

    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }




    fun doneToastErrorUsername() {
        _errorToastUsername.value = false
    }

    fun doneToastErrorUsernameInvalid() {
        _errorToastUsernameInvalid.value = false
    }

    fun doneToastInvalidPassword() {
        _errorToastInvalidPassword.value = false
    }
    fun doneToastUsernameNotExit() {
        _errorToastUsernameNotExit.value = false
    }
    fun doneToastPassword() {
        _errorToastPassword.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}