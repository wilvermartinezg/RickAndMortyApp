package com.wilvermartinez.rickandmortyapp.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: OriginModel,
    val location: LocationModel,
    val episode: List<String>
)

data class OriginModel(
    val name: String
)

data class LocationModel(
    val name: String
)
