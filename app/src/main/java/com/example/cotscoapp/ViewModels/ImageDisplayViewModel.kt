package com.example.cotscoapp.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cotscoapp.Models.ImageDisplayModel

class ImageDisplayViewModel: ViewModel() {
    val imageDisplayModel = MutableLiveData<ImageDisplayModel>()

    fun setImageDisplayModel(newImageDisplayModel: ImageDisplayModel) {
        imageDisplayModel.value = newImageDisplayModel
    }
}