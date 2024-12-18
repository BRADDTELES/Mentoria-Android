package com.application.desafiotestedeempregosimulacao.model

data class AdConfig(
    val highRiskFlags: List<Any>,
    val high_risk_flags: List<Any>,
    val nsfw_score: Double,
    val safeFlags: List<String>,
    val safe_flags: List<String>,
    val showAdLevel: Int,
    val show_ad_level: Int,
    val show_ads: Boolean,
    val showsAds: Boolean,
    val unsafeFlags: List<String>,
    val unsafe_flags: List<String>,
    val wallUnsafeFlags: List<String>,
    val wall_unsafe_flags: List<String>
)