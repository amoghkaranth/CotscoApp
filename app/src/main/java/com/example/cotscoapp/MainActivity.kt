package com.example.cotscoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.android.volley.*
import com.android.volley.toolbox.*
import com.example.cotscoapp.Fragments.ImageDisplayFragment
import com.example.cotscoapp.Models.ImageDisplayModel
import com.example.cotscoapp.ViewModels.ImageDisplayViewModel
import com.example.cotscoapp.databinding.ActivityMainBinding

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageDisplayModel: ImageDisplayModel
    private val imageDisplayViewModel: ImageDisplayViewModel by viewModels()
    private val searchErrorCheck = hashMapOf("Messi" to "Messi", "Won" to "Won", "The" to "The", "World" to "World", "Cup" to "Cup")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(searchErrorCheck.containsKey(query)) {
                    loadContents()
                    imageDisplayModel = ImageDisplayModel("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png", query)
                    imageDisplayViewModel.setImageDisplayModel(imageDisplayModel)
                    loadFragment(ImageDisplayFragment())
                } else {
                    Toast.makeText(this@MainActivity, "Please Enter a Valid City Name", Toast.LENGTH_SHORT).show()
                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        setContentView(binding.root)
    }

    private fun loadFragment(fragment:Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.imageFragment, fragment)
            .commit()
    }

    private fun loadContents() {

        val url = "https://meme-api.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("TAG", "Response is Valid Man: $response")

//                val thumbnail = response.getString("thumbnail")

            },
            {
//                it.localizedMessage?.let { it1 -> Log.d("error", it1) }
                if (it is TimeoutError || it is NoConnectionError){
                    Log.d(TAG, "Response is Timeout or NoConnectionError")
                } else if (it is AuthFailureError) {
                    Log.d(TAG, "Response is AuthFailureError")
                } else if (it is  ServerError) {
                    Log.d(TAG, "Response is ServerError")
                } else if (it is NetworkError) {
                    Log.d(TAG, "NetworkError is ServerError")
                } else if (it is ParseError) {
                    Log.d(TAG, "Response is ParseError")
                }
            })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


}