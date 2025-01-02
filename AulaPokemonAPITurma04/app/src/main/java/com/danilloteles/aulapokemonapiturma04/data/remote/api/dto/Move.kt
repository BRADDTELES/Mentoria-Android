package com.danilloteles.aulapokemonapiturma04.data.remote.api.dto

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)