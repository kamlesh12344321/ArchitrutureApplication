package com.android.mvi.uis.intents

sealed class DataIntent {
    object FetchData :DataIntent()
}
