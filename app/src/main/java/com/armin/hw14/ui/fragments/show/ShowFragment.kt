package com.armin.hw14.ui.fragments.show

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.armin.hw14.R
import com.armin.hw14.data.network.Model.PhotoJson
import com.armin.hw14.data.network.NEtworkManager.api
import com.armin.hw14.databinding.ActivityMainBinding
import com.armin.hw14.databinding.FragmentShowBinding
import com.armin.hw14.ui.adapter.PhotoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class ShowFragment : Fragment(R.layout.fragment_show) {
    val photoAdapter = PhotoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShowBinding.bind(view)

        binding.apply {
            recyclerViewShowFragment.apply {
                adapter = photoAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        getImageCurrent()
    }
    private fun getImageCurrent() {
        Log.d("H14 Debug", "Start Function (Show Fragment)")
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("PHOTOIMAGE", "Started GlobalScope (Show Fragment)")
            Log.d("H14 Debug", "A Wait for response (Show Frafment)")
            val response: Response<PhotoJson> = api
                .getForShowPhoto(
                    "1c04e05bce6e626247758d120b372a73",
                    "flickr.photos.getRecent",
                    "34427466731@N01",
                    "url_s",
                    "json",
                    1,
                    30
                )
                .awaitResponse()
            if (response.isSuccessful) {
                Log.d("H14 Debug", "response isSuccessful (Show Fragment) ")
                Log.d("H14 Debug", "Data (Show Fragment) :")
                val data = response.body()!!.photos
                Log.d("H14 Debug", data.photo.toString())
                photoAdapter.submitList(data.photo.toMutableList())
                Log.d("H14 Debug", "Send List to Adapter (Show Fragment) ")
            }
        }
    }
}