package com.stupa.userDetails

import androidx.lifecycle.LiveData
import com.stupa.db.RegisterDao
import com.stupa.db.RegisterEntity

class UserDetailsRepository(dao: RegisterDao) {
    val users = dao.getAllUsers()
}