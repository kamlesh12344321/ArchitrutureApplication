package com.android.mvi.data.model



data class Data(
    val `data`: List<Users>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)