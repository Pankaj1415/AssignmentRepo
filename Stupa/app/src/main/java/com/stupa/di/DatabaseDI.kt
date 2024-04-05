package com.stupa.di

import android.content.Context
import androidx.room.Room
import com.stupa.db.RegisterDatabase
import com.stupa.constants.Constants.USER_DATABASE

fun provideDatabase(context: Context) = Room.databaseBuilder(
    context, RegisterDatabase::class.java,
    USER_DATABASE
).allowMainThreadQueries().fallbackToDestructiveMigration().build()

fun provideDao(db: RegisterDatabase) = db.registerDao
