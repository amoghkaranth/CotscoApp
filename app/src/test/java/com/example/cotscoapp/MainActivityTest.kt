package com.example.cotscoapp

import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private var mUserId: Int = 0
    private var mId: Int = 0
    private var mTitle: String = ""
    private var mCompleted: Boolean = false

    @Before
    fun setUp() {
        loadContents()
    }

    private fun loadContents() {

        val url = "https://jsonplaceholder.typicode.com/todos/1"

        JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("TAG", "Response is Valid: $response")
                mUserId = response.getInt("userId")
                mId = response.getInt("id")
                mTitle = response.getString("title")
                mCompleted = response.getBoolean("completed")
            },
            {
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

    }

    @Test
    fun check_user_id() {
        assertTrue(mUserId == 1)
    }

    @Test
    fun check_id() {
        assertTrue(mId == 1)
    }

    @Test
    fun check_title() {
        assertTrue(mTitle == "delectus aut autem")
    }

    @Test
    fun check_completed() {
        assertFalse(mCompleted)
    }

}