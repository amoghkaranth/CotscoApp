package com.example.cotscoapp.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cotscoapp.Models.ImageDisplayModel
import com.example.cotscoapp.databinding.DetailedImageDisplayLayoutBinding
import com.squareup.picasso.Picasso

class DetailedImageDisplayActivity : AppCompatActivity(){
    private lateinit var binding: DetailedImageDisplayLayoutBinding
    private lateinit var mData: ImageDisplayModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedImageDisplayLayoutBinding.inflate(layoutInflater)

        mData = intent.getParcelableExtra("KEY")!!
        binding.tempText.text = mData.message
        Picasso.get().load(mData.url).into(binding.detailedImageView)


        //FIGURE OUT HOW TO HIDE IN THE MANIFEST
        supportActionBar?.hide()

        val customActionBar = binding.customActionBar
        customActionBar.backButton.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

}