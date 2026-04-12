package com.nicos.datastoreencryption.data_store

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesDataStoreEncryptionModule {

    @Singleton
    @Provides
    fun providePreferencesDataStoreEncryption(
        @ApplicationContext context: Context
    ): PreferencesDataStoreEncryption {
        return PreferencesDataStoreEncryption(context)
    }
}