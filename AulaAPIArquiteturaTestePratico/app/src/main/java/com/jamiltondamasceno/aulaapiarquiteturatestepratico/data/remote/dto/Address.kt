package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto

data class Address(
    val address: String,
    val city: String,
    val coordinates: Coordinates,
    val postalCode: String,
    val state: String
)