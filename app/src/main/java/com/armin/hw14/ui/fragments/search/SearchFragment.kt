package com.armin.hw14.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.armin.hw14.R
import com.armin.hw14.data.network.Model.PhotoJson
import com.armin.hw14.data.network.NEtworkManager
import com.armin.hw14.databinding.FragmentSearchBinding
import com.armin.hw14.databinding.FragmentShowBinding
import com.armin.hw14.ui.adapter.SearchAdapter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class SearchFragment : Fragment(R.layout.fragment_search) {
    val searchAdapter = SearchAdapter()
    private lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)
        binding.apply {
            searchRecyclerView.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<PhotoJson> = NEtworkManager.api
                        .getSearch(
                            "1c04e05bce6e626247758d120b372a73",
                            "flickr.photos.getRecent",
                            "34427466731@N01",
                            "url_s",
                            "json",
                            1,
                            30,
                            "$p0"
                        )
                        .awaitResponse()
                    if (response.isSuccessful) {
                        val data = response.body()!!.photos
                        searchAdapter.submitList(data.photo.toMutableList())
                    }
                }
              //  val msg = if (isMatchFound) "Found: $p0" else getString(R.string.no_match)
              //  Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                GlobalScope.launch(Dispatchers.IO) {
                    val response: Response<PhotoJson> = NEtworkManager.api
                        .getSearch(
                            "1c04e05bce6e626247758d120b372a73",
                            "flickr.photos.getRecent",
                            "34427466731@N01",
                            "url_s",
                            "json",
                            1,
                            30,
                            "$p0"
                        )
                        .awaitResponse()
                    if (response.isSuccessful) {
                        val data = response.body()!!.photos
                        searchAdapter.submitList(data.photo.toMutableList())
                    }
                }
                return false
            }
        })
    }

}