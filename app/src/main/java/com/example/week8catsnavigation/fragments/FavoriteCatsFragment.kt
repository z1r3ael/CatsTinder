package com.example.week8catsnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.week8catsnavigation.MainActivity
import com.example.week8catsnavigation.R
import com.example.week8catsnavigation.data.CatData
import com.example.week8catsnavigation.data.FavCatsAdapter
import com.example.week8catsnavigation.database.AppDatabase
import com.example.week8catsnavigation.databinding.FragmentFavoriteCatsBinding
import com.example.week8catsnavigation.fragments.contract.HasCustomTitle
import kotlinx.coroutines.*

class FavoriteCatsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentFavoriteCatsBinding
    private val job = Job()
    private val myScope = CoroutineScope(Dispatchers.Main + job)

    private var favoriteCatsFromDatabase: ArrayList<CatData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteCatsBinding.inflate(inflater, container, false)

        val database = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "FAVORITE_CATS_DATABASE"
        ).build()
        val favoriteCatDao = database.favoriteCatDao()

        myScope.launch(Dispatchers.IO) {
            if (favoriteCatDao.get() == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Вы пока не лайкнули ни одного котика", Toast.LENGTH_SHORT).show()
                }
            }

            if (favoriteCatDao.get() != null) {
                val favoriteCatsListFromDatabase: List<CatData> = favoriteCatDao.getAll()
                favoriteCatsListFromDatabase.forEach {
                    favoriteCatsFromDatabase.add(it)
                }
                val favCatsAdapter = FavCatsAdapter(favoriteCatsFromDatabase)
                withContext(Dispatchers.Main) {
                    binding.favoriteCatsRecyclerView.adapter = favCatsAdapter
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Избранные котики загружены из базы данных", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.favoriteCatsRecyclerView.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun getTitleRes(): Int = R.string.fav_cats_title
}