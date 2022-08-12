package com.example.foodapp.di

import android.content.Context
import androidx.room.Room
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.repo.KampanyaRepository
import com.example.foodapp.repo.SepetRepository
import com.example.foodapp.repo.YemeklerRepository
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.SepetDao
import com.example.foodapp.retrofit.YemeklerDao
import com.example.foodapp.room.KampanyaDao
import com.example.foodapp.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Provides
    @Singleton
    fun provideSepetDaoRepository(sdao:SepetDao) : SepetRepository{
        return SepetRepository(sdao)
    }

    @Provides
    @Singleton
    fun provideSepetDao(): SepetDao{
        return ApiUtils.getSepetDao()
    }
    @Provides
    @Singleton
    fun provideKampanyaDaoRepository(kdao:KampanyaDao) : KampanyaRepository{
        return KampanyaRepository(kdao)
    }

    @Provides
    @Singleton
    fun provideKampanyaDao(@ApplicationContext context: Context): KampanyaDao{
        val vt = Room.databaseBuilder(context,Veritabani::class.java,"kampanya.sqlite")
            .createFromAsset("kampanya.sqlite").build()
        return vt.getKampanyaDao()
    }

    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao:YemeklerDao) : YemeklerRepository{
        return YemeklerRepository(ydao)
    }
    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }



}