package com.stupa.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stupa.constants.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(registerEntity: RegisterEntity)

    @Query("SELECT * FROM ${Constants.REGISTER_TABLE} order by userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>


    @Query("SELECT * FROM ${Constants.REGISTER_TABLE} WHERE email LIKE :email")
    suspend fun getUsername(email: String): RegisterEntity?
}