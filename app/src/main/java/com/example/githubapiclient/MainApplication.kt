package com.example.githubapiclient

import android.app.Application
import com.example.githubapiclient.data.di.NetworkModule
import com.example.githubapiclient.data.di.RepositoryModule
import com.example.githubapiclient.presentation.view.MainActivity
import com.example.githubapiclient.presentation.view.MainFragment
import com.example.githubapiclient.presentation.viewmodel.DetailsViewModel
import com.example.githubapiclient.presentation.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

class MainApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun injectMainViewModel(viewModel: MainViewModel)
    fun injectDetailsViewModel(viewModel: DetailsViewModel)
}