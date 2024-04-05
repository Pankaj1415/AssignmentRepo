package com.stupa.userDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stupa.db.RegisterEntity
import com.stupa.db.RegisterRepository

class UserDetailsViewModel (private val repository: UserDetailsRepository, application: Application):AndroidViewModel(application){


    val users = repository.users


//    private var _usersList = MutableLiveData<List<RegisterEntity>>()

//    var userList  = LiveData<List<RegisterEntity>>()
//        get() = _usersList

    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    fun doneNavigating(){
        _navigateto.value = false
    }

    fun backButtonclicked(){
        _navigateto.value = true
    }
    suspend fun getAllUsers(){
//        userList =  repository.getAllUsers()
    }

}