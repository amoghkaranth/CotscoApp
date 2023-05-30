package com.example.cotscoapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cotscoapp.Activities.DetailedImageDisplayActivity
import com.example.cotscoapp.Models.ImageDisplayModel
import com.example.cotscoapp.RecyclerViewAdapter
import com.example.cotscoapp.ViewModels.ImageDisplayViewModel
import com.example.cotscoapp.databinding.FragmentImageDisplayBinding

const val TAG = "ImageDisplayFragment"

/**
 * Fragment used to display the images and associated text of the respective search results
 */

class ImageDisplayFragment : Fragment() , RecyclerViewAdapter.OnItemListener {

    private val mImageDisplayViewModel: ImageDisplayViewModel by activityViewModels()
    private lateinit var binding: FragmentImageDisplayBinding
    private lateinit var mImageDisplayModel: ImageDisplayModel
    private var mImageDisplayModelsList: Array<ImageDisplayModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageDisplayBinding.inflate(inflater, container, false)

        mImageDisplayViewModel.imageDisplayModel.observe(viewLifecycleOwner) {
            mImageDisplayModel = it
            Log.d(TAG, "Restaurants Model: $it")
            mImageDisplayModelsList = arrayOf(it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it)
            setupRecyclerView()
        }

        return binding.root
    }

    /**
     * Method used to setup RecyclerView containing the images and associated text
     */
    private fun setupRecyclerView() {
        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 5)
        val adapter = RecyclerViewAdapter(context, mImageDisplayModelsList, this)
        recyclerView.apply {
            this.adapter = adapter
        }
    }

    companion object {
        private const val KEY = "KEY"
    }

    /**
     * Implementation of the method in the interface within the RecyclerView Adapter
     */
    override fun onItemClick(position: Int) {
        Log.d(TAG, "Position $position clicked")
        val intent = Intent(context, DetailedImageDisplayActivity::class.java)
        intent.putExtra(KEY, mImageDisplayModelsList?.get(position))
        context?.startActivity(intent)
    }

}