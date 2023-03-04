package com.example.dictionarycompose.feature_dictionary.data.repository

import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.feature_dictionary.data.local.WordInfoDao
import com.example.dictionarycompose.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionarycompose.feature_dictionary.domain.model.WordInfo
import com.example.dictionarycompose.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao : WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word : String) : Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordsInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })

        } catch (e: HttpException){
            emit(Resource.Error("HTTP EXCEPTION", data = wordInfos))
        } catch (e: IOException){
            emit(Resource.Error("IO EXCEPRION", data = wordInfos))
        }
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}