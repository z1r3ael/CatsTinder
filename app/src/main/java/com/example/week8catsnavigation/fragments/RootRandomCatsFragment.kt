package com.example.week8catsnavigation.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.week8catsnavigation.MainActivity
import com.example.week8catsnavigation.R
import com.example.week8catsnavigation.data.CatData
import com.example.week8catsnavigation.data.CatService
import com.example.week8catsnavigation.database.AppDatabase
import com.example.week8catsnavigation.databinding.FragmentRootRandomCatsBinding
import com.example.week8catsnavigation.fragments.contract.HasCustomTitle
import kotlinx.coroutines.*

class RootRandomCatsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentRootRandomCatsBinding

    private val job = Job()
    private val myScope = CoroutineScope(Dispatchers.Main + job)

    private val service = CatService.create()
    private lateinit var currentCat: List<CatData>
    private var imageUrl: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRootRandomCatsBinding.inflate(inflater, container, false)

        val database = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "FAVORITE_CATS_DATABASE"
        ).build()
        val favoriteCatDao = database.favoriteCatDao()

        getRandomCatImage()

        binding.likeButton.setOnClickListener {
            myScope.launch(Dispatchers.IO) {
                favoriteCatDao.insert(currentCat[0])
            }
            getRandomCatImage()
        }

        binding.dislikeButton.setOnClickListener {
            getRandomCatImage()
        }

        binding.favoriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_rootRandomCatsFragment_to_favoriteCatsFragment)
        }

        return binding.root
    }

    private fun getRandomCatImage() {
        myScope.launch(Dispatchers.IO) {
            currentCat = service.getAndPrintCat()
            withContext(Dispatchers.Main) {
                imageUrl = currentCat[0].catImageUrl
                val uri = Uri.parse(imageUrl)
                binding.catImageView.setImageURI(uri)
            }
        }
    }

    override fun getTitleRes(): Int = R.string.root_screen_title
}