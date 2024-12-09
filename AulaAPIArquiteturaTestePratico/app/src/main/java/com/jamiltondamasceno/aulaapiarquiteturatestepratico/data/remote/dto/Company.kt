package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto

import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Address

data class Company(
    val address: Address,
    val department: String,
    val name: String,
    val title: String
)