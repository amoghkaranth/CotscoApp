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

/**
 * Homepage which user can search for cities
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageDisplayModel: ImageDisplayModel
    private val imageDisplayViewModel: ImageDisplayViewModel by viewModels()
    private val searchErrorCheck = hashMapOf("Seattle" to "https://images.vexels.com/media/users/3/144140/isolated/preview/a2df1a6c99b32b078a1975d5788e27ac-seattle-skyline-badge.png",
                                            "Chicago" to "https://images.vexels.com/media/users/3/144056/isolated/preview/116d60e8fd9e3e2233abbb71a6ae5d9e-chicago-skyline-badge.png",
                                            "New York" to "https://images.vexels.com/media/users/3/144125/isolated/preview/e41e827336e592fc084566be2bff2665-new-york-skyline-badge-vector.png",
                                            "Los Angeles" to "https://www.pngall.com/wp-content/uploads/9/Los-Angeles-PNG-Image.png",
                                            "Miami" to "https://images.vexels.com/media/users/3/144114/isolated/lists/fa91eba1385b90b0bf97e3365b515c74-miami-city-badge.png")
    private var mUserId: Int = 0
    private var mId: Int = 0
    private var mTitle: String = ""
    private var mCompleted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        searchCity()
        setContentView(binding.root)
    }

    /**
     * Will display fragment when appropriate items are searched for
     */
    private fun searchCity() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(searchErrorCheck.containsKey(query)) {
                    loadContents()
                    imageDisplayModel = searchErrorCheck[query]
                        ?.let { ImageDisplayModel(it, query, mUserId, mId, mTitle, mCompleted) }!!
                    imageDisplayViewModel.setImageDisplayModel(imageDisplayModel)
                    loadFragment(ImageDisplayFragment())
                } else {
                    Toast.makeText(this@MainActivity, R.string.invalid_search, Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    /**
     * Loads fragment onto MainActivity
     */
    private fun loadFragment(fragment:Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.imageFragment, fragment)
            .commit()
    }


    /**
     * Performs API call through Volley Library
     */
    private fun loadContents() {

        val queue = Volley.newRequestQueue(this)

        val url = "https://jsonplaceholder.typicode.com/todos/1"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("TAG", "Response is Valid: $response")
                mUserId = response.getInt("userId")
                mId = response.getInt("id")
                mTitle = response.getString("title")
                mCompleted = response.getBoolean("completed")
            },
            {
                if (it is TimeoutError){
                    Log.d(TAG, "Response is Timeout")
                } else if ( it is NoConnectionError) {
                    Log.d(TAG, "Response is NoConnectionError")
                }else if (it is AuthFailureError) {
                    Log.d(TAG, "Response is AuthFailureError")
                } else if (it is  ServerError) {
                    Log.d(TAG, "Response is ServerError")
                } else if (it is NetworkError) {
                    Log.d(TAG, "NetworkError is ServerError")
                } else if (it is ParseError) {
                    Log.d(TAG, "Response is ParseError")
                }
            })

        queue.add(jsonObjectRequest)

    }


}