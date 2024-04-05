package com.stupa.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class], version = 2, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract val registerDao: RegisterDao

}

