package com.test_app.open_weater.di

import androidx.lifecycle.liveData
import com.test_app.open_weater.models.Repository
import com.test_app.open_weater.models.RepositoryImpl
import com.test_app.open_weater.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl()  }
    viewModel{ MainViewModel(get())}
}