package org.newsapi.newsarticles.common.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.newsapi.newsarticles.common.Constants.DB_NAME
import org.newsapi.newsarticles.data.data_source.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()
    }


}