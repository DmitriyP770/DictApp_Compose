package com.example.dictionarycompose.feature_dictionary.data.remote.dto

import com.example.dictionarycompose.feature_dictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
){
    fun toDefinition() = Definition(antonyms, definition, example, synonyms)
}