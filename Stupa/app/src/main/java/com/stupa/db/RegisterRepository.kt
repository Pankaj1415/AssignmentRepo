package com.stupa.db

class RegisterRepository(private val dao: RegisterDao) {

    suspend fun insert(user: RegisterEntity) {
        return dao.saveUser(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }

}