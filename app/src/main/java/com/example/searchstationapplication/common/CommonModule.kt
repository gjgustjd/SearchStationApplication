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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun getSubwayStationAPI(): SubwayStationAPI {
        val gson = GsonBuilder().setLenient().create()
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(SubwayStationAPI::class.java)
    }

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): LocalStationDataBase =
        Room.databaseBuilder(context, LocalStationDataBase::class.java, "stations")
            .fallbackToDestructiveMigration().build()

}