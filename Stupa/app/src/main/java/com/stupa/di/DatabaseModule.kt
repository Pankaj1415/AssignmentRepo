package com.stupa.di


import com.stupa.db.RegisterRepository
import com.stupa.userDetails.UserDetailsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single {  provideDao(get()) }
    factory {  RegisterRepository(get()) }
    factory {  UserDetailsRepository(get()) }
}