package com.example.application8

import android.app.Application
import com.example.application8.container.AppContainer
import com.example.application8.container.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}