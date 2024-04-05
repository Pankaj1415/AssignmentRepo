package com.stupa.di

import com.stupa.util.SharedPreference
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferenceModule = module {
    single {
        SharedPreference()
    }
    single {
        androidApplication().getSharedPreferences("Stupa", android.content.Context.MODE_PRIVATE)
    }
}