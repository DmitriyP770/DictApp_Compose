package com.example.dictionarycompose.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.example.dictionarycompose.feature_dictionary.data.local.Converters
import com.example.dictionarycompose.feature_dictionary.data.local.WordInfoDao
import com.example.dictionarycompose.feature_dictionary.data.local.WordInfoDatabase
import com.example.dictionarycompose.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionarycompose.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.example.dictionarycompose.feature_dictionary.data.util.GsonParser
import com.example.dictionarycompose.feature_dictionary.domain.repository.WordInfoRepository
import com.example.dictionarycompose.feature_dictionary.domain.use_case.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideUseCase(repository : WordInfoRepository) = GetWordInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideWordInfoReopsitory(
        db : WordInfoDatabase,
        api : DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(api, db.dao)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app ,
        WordInfoDatabase::class.java ,
        "word_db"
    ).addTypeConverter(Converters(GsonParser(Gson())))
        .build()

    @Provides
    @Singleton
    fun provideApi() = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)
}