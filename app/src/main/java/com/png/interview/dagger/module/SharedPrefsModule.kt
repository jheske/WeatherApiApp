package com.png.interview.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
object SharedPreferencesModule {
    @Provides
    fun sharedPrefsModule(application: Application): SharedPreferences {
        return application.getSharedPreferences("APP_SHARED_PREFS", Context.MODE_PRIVATE)
    }
}