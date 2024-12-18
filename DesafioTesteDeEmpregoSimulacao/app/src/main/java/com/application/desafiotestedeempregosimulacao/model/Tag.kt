package com.application.desafiotestedeempregosimulacao.model

data class Tag(
    val accent: String,
    val background_hash: String,
    val background_is_animated: Boolean,
    val description: String,
    val description_annotations: DescriptionAnnotations,
    val display_name: String,
    val followers: Int,
    val following: Boolean,
    val is_promoted: Boolean,
    val is_whitelisted: Boolean,
    val logo_destination_url: Any,
    val logo_hash: Any,
    val name: String,
    val thumbnail_hash: String,
    val thumbnail_is_animated: Boolean,
    val total_items: Int
)