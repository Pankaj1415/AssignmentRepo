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
}