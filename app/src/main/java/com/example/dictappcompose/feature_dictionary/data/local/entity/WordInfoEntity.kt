package com.example.dictionarycompose.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionarycompose.feature_dictionary.domain.model.Meaning
import com.example.dictionarycompose.feature_dictionary.domain.model.WordInfo

@Entity(tableName = "wordinfoentity")
data class WordInfoEntity(
    val meanings: List<Meaning> ,
    val phonetic: String ,
    val word: String,
    @PrimaryKey
    val id: Int? = null
){
    fun toWordInfo() = WordInfo(meanings, phonetic, word)
}
