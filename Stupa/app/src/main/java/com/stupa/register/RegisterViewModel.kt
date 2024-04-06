package com.stupa.register

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.stupa.constants.Constants
import com.stupa.db.RegisterEntity
import com.stupa.db.RegisterRepository
import com.stupa.util.SharedPreference
import com.stupa.util.isValidEmail
import kotlinx.coroutines.*


class RegisterViewModel(private val repository: RegisterRepository,

                        private val sharedPreference: SharedPreference,

                        application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "init")
    }



    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()


    @Bindable
    val inputName = MutableLiveData<String?>()

    var inputCountryCode : String = ""

    @Bindable
    val inputPhone = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()


    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto


    private val _errorToastName = MutableLiveData<Boolean>()

    val errorToastName: LiveData<Boolean>
        get() = _errorToastName


    private val _errorToastPhone = MutableLiveData<Boolean>()

    val errorToastPhone: LiveData<Boolean>
        get() = _errorToastPhone

    private val _errorToastPhoneLength = MutableLiveData<Boolean>()

    val errorToastPhoneLength: LiveData<Boolean>
        get() = _errorToastPhoneLength


    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errorToastEmail: LiveData<Boolean>
        get() = _errorToastEmail


    private val _errorToastPassword = MutableLiveData<Boolean>()

    val errorToastPassword: LiveData<Boolean>
        get() = _errorToastPassword

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    private val _errorToastUsernameInvalid = MutableLiveData<Boolean>()

    val errotoastUsernameInvalid: LiveData<Boolean>
        get() = _errorToastUsernameInvalid

    fun donetoastErrorUsernameInvalid() {
        _errorToastUsernameInvalid.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    fun registerSubmitButton() {
        if (inputName.value == null) {
            _errorToastName.value = true
        } else if (inputPhone.value == null) {
            _errorToastPhone.value = true
        } else if (inputPhone.value!!.length<10) {
            _errorToastPhoneLength.value = true
        }
        else if (inputEmail.value == null) {
            _errorToastEmail.value = true
        } else if (!inputEmail.value!!.isValidEmail()) {
            _errorToastUsernameInvalid.value = true
        }
        else if (inputPassword.value?.trim().isNullOrEmpty()) {
            _errorToastPassword.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputEmail.value!!)
                Log.i("MYTAG", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", userDetailsLiveData.value.toString() + "ASDFASDFASDFASDF")
                    Log.i("MYTAG", "OK im in")
                    val name = inputName.value!!
                    val countryCode = inputCountryCode
                    val phone = inputPhone.value!!
                    val email = inputEmail.value!!
                    val password = inputPassword.value.toString().trim()
                    Log.i("MYTAG", "insidi Sumbit")
                    insert(RegisterEntity(0, name, countryCode, phone, email, password))
                    inputName.value = null
                    inputCountryCode = ""
                    inputPhone.value = null
                    inputEmail.value = null
                    inputPassword.value = null
                    _navigateto.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateto.value = false
        Log.i("MYTAG", "Done navigating ")
    }


    fun doneToastName() {
        _errorToastName.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    fun doneToastPhone() {
        _errorToastPhone.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    fun doneToastPhoneLength() {
        _errorToastPhoneLength.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    fun doneToastEmail() {
        _errorToastEmail.value = false
        Log.i("MYTAG", "Done taoasting ")
    }
    fun doneToastPassword() {
        _errorToastPassword.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastUserName() {
        _errorToastUsername.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}





