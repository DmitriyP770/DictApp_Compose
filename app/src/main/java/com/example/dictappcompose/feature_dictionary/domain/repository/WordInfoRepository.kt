package com.example.dictionarycompose.feature_dictionary.domain.repository

import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}