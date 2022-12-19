package com.example.cotscoapp.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageDisplayModel (
    val url: String,
    val message: String) : Parcelable
