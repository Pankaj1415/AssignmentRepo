package com.stupa.di

import com.stupa.login.LoginViewModel
import com.stupa.register.RegisterViewModel
import com.stupa.userDetails.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserDetailsViewModel(get(),get()) }
    viewModel { RegisterViewModel(get(),get(),get()) }
    viewModel { LoginViewModel(get(),get(),get() ) }

}