package com.techjd.itunesapi.ui.artists

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.techjd.itunesapi.R
import com.techjd.itunesapi.adapters.ArtistsAdapter
import com.techjd.itunesapi.data.artists.Artists
import com.techjd.itunesapi.data.artists.ArtistsDatabase
import com.techjd.itunesapi.databinding.FragmentArtistsBinding
import com.techjd.itunesapi.util.InternetConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artists.*
import javax.inject.Inject

@AndroidEntryPoint
class ArtistsFragment : Fragment(R.layout.fragment_artists) {

    private lateinit var viewModel: ArtistsViewModel
    private lateinit var binding: FragmentArtistsBinding

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var artistsDatabase: ArtistsDatabase


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentArtistsBinding.inflate(layoutInflater)
        val view = binding.root
        super.onViewCreated(view, savedInstanceState)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        if (context?.let { InternetConnection.checkConnection(it) } == true) {

            viewModel =
                ViewModelProvider(this@ArtistsFragment).get(ArtistsViewModel::class.java)
            searchViewOnline.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    progress_bar.isVisible = true
                    viewModel.makeCall()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    progress_bar.isVisible = true
                    viewModel.query.value = newText
                    viewModel.makeCall()
                    return false
                }
            })

            viewModel.getList().observe(viewLifecycleOwner, { artists ->
                if (artists != null) {
                    recyclerView.adapter = ArtistsAdapter(
                        glide,
                        artists.results as ArrayList<Artists>
                    )
                    progress_bar.isVisible = false
                } else {
                    progress_bar.isVisible = true
                }
            })
            viewModel.makeCall()
        } else {
            searchViewOnline.isVisible = false
            errorText.isVisible = true
            errorText.text = "No Internet Connection"
        }


    }


}
