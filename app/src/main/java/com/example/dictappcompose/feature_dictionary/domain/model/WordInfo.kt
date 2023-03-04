package com.example.dictionarycompose.feature_dictionary.domain.model

import com.example.dictionarycompose.feature_dictionary.data.remote.dto.MeaningDto
import com.example.dictionarycompose.feature_dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning> ,
    val phonetic: String ,
    val word: String
)
