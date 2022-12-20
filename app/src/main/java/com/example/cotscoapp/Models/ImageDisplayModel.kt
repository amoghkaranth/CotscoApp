package com.example.cotscoapp.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Business logic used by ViewModels and Views
 */

@Parcelize
data class ImageDisplayModel (
    val url: String,
    val message: String,
    val userId: Int,
    val id: Int,
    val title: String,
    val mCompleted: Boolean) : Parcelable
