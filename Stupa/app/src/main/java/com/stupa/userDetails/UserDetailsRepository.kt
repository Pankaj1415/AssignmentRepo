package com.stupa.userDetails

import androidx.lifecycle.LiveData
import com.stupa.db.RegisterDao
import com.stupa.db.RegisterEntity

class UserDetailsRepository(private val dao: RegisterDao) {

    val users = dao.getAllUsers()
    suspend fun getAllUsers() : LiveData<List<RegisterEntity>> {
        return dao.getAllUsers()
    }



}