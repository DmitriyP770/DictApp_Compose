package com.example.dictionarycompose.feature_dictionary.data.remote.dto

import com.example.dictionarycompose.feature_dictionary.data.local.entity.WordInfoEntity
import com.example.dictionarycompose.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto> ,
    val phonetic: String ,
    val phonetics: List<PhoneticDto> ,
    val word: String
){
    fun toWordInfo() = WordInfo(
        meanings = meanings.map { it.toMeaning() } ,
        phonetic ,
        word
    )

    fun toWordInfoEntity() = WordInfoEntity(
        meanings.map { it.toMeaning() } ,
        phonetic ,
        word
    )
}