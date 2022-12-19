package com.example.cotscoapp.ViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cotscoapp.Models.ImageDisplayModel

class ImageDisplayViewModel: ViewModel() {
    val url = MutableLiveData<String>()
    val imageDisplayModel = MutableLiveData<ImageDisplayModel>()

    fun setStr(newUrl: String) {
        url.value = newUrl
    }

    fun setImageDisplayModel(newImageDisplayModel: ImageDisplayModel) {
        imageDisplayModel.value = newImageDisplayModel
    }
}