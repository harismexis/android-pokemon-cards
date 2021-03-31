package com.harismexis.pokemon.datamodel.uimodel

data class PokemonUiModel(
    var id: String,
    var name: String?,
    var imageUrl: String?,
    var imageUrlHiRes: String?,
    var supertype: String?,
    var subtype: String?,
    var artist: String?,
    var rarity: String?,
    var series: String?,
    var set: String?,
    var setCode: String?
)