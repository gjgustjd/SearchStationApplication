package com.example.searchstationapplication.common

import android.content.Context
import androidx.room.Room
import com.example.searchstationapplication.model.MainRepository.Companion.BASE_URL
import com.example.searchstationapplication.model.SubwayStationAPI
import com.example.searchstationapplication.model.local.LocalStationDataBase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun getSubwayStationAPI(): SubwayStationAPI {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(SubwayStationAPI::class.java)
    }

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): LocalStationDataBase =
        Room.databaseBuilder(context, LocalStationDataBase::class.java, "user-db")
            .fallbackToDestructiveMigration().build()

}