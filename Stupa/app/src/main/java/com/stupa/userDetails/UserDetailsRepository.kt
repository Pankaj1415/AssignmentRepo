package com.stupa.userDetails

import com.stupa.db.RegisterDao

class UserDetailsRepository(dao: RegisterDao) {
    val users = dao.getAllUsers()
}