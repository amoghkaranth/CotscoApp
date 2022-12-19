package com.example.cotscoapp.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cotscoapp.Models.ImageDisplayModel
import com.example.cotscoapp.databinding.DetailedImageDisplayLayoutBinding
import com.squareup.picasso.Picasso

class DetailedImageDisplayActivity : AppCompatActivity(){
    private lateinit var binding: DetailedImageDisplayLayoutBinding
    private lateinit var mImageDisplayModel: ImageDisplayModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailedImageDisplayLayoutBinding.inflate(layoutInflater)
        mImageDisplayModel = intent.getParcelableExtra("KEY")!!
        loadViews()
        createCustomActionBar()

        setContentView(binding.root)
    }

    private fun loadViews() {
        binding.tempText.text = mImageDisplayModel.message
        Picasso.get().load(mImageDisplayModel.url).into(binding.detailedImageView)
    }

    private fun createCustomActionBar() {
        //FIGURE OUT HOW TO HIDE IN THE MANIFEST

        supportActionBar?.hide()
        val customActionBar = binding.customActionBar
        customActionBar.backButton.setOnClickListener {
            finish()
        }
    }

}