package com.example.cotscoapp.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cotscoapp.Models.ImageDisplayModel

/**
 * ViewModel class used to store the values of the ImageDisplayModel
 * Helps connect the communication between MainActivity and ImageDisplayFragment
 */

class ImageDisplayViewModel: ViewModel() {
    val imageDisplayModel = MutableLiveData<ImageDisplayModel>()

    fun setImageDisplayModel(newImageDisplayModel: ImageDisplayModel) {
        imageDisplayModel.value = newImageDisplayModel
    }
}