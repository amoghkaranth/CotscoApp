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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const val TAG = "ImageDisplayFragment"

class ImageDisplayFragment() : Fragment() , RecyclerViewAdapter.OnItemListener {

    private var param1: String? = null
    private var param2: String? = null
    private val imageDisplayViewModel: ImageDisplayViewModel by activityViewModels()
    private lateinit var binding: FragmentImageDisplayBinding
    private lateinit var mImageDisplayModel: ImageDisplayModel
    private var mData: Array<ImageDisplayModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageDisplayBinding.inflate(inflater, container, false)

        imageDisplayViewModel.imageDisplayModel.observe(viewLifecycleOwner) {
            mImageDisplayModel = it
            Log.d(TAG, "Restuarants Modelo: $it")

            mData = arrayOf(it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it)
            val recyclerView : RecyclerView = binding.recyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 5)
            val adapter = RecyclerViewAdapter(context, mData, this)
            recyclerView.adapter = adapter
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageDisplayFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageDisplayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, "Position $position clicked")
        val intent = Intent(context, DetailedImageDisplayActivity::class.java)

        intent.putExtra("KEY", mData?.get(position))

        context?.startActivity(intent)
    }

}