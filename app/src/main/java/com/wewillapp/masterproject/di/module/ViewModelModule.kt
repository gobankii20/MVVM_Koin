package com.wewillapp.masterproject.di.module

import com.wewillapp.masterproject.view.base.ToolbarViewModel
import com.wewillapp.masterproject.view.login.LoginViewModel
import com.wewillapp.masterproject.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ToolbarViewModel() }

    viewModel { LoginViewModel(get()) }

    viewModel { MainViewModel(get()) }
}