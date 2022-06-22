package com.example.week8catsnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.week8catsnavigation.databinding.ActivityMainBinding
import com.example.week8catsnavigation.fragments.AboutAppFragment
import com.example.week8catsnavigation.fragments.FavoriteCatsFragment
import com.example.week8catsnavigation.fragments.RootRandomCatsFragment
import com.example.week8catsnavigation.fragments.contract.HasCustomTitle
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        when (destination.id) {
            R.id.rootRandomCatsFragment -> {
                binding.toolbar.title = getString(RootRandomCatsFragment().getTitleRes())
                binding.toolbar.menu.findItem(R.id.about).isVisible = true
            }
            R.id.favoriteCatsFragment -> {
                binding.toolbar.title = getString(FavoriteCatsFragment().getTitleRes())
                binding.toolbar.menu.findItem(R.id.about).isVisible = true
            }
            R.id.aboutAppFragment -> {
                binding.toolbar.title = getString(AboutAppFragment().getTitleRes())
                binding.toolbar.menu.findItem(R.id.about).isVisible = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener(listener)
    }

    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(listener)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about) {
            findNavController(R.id.fragmentContainer).navigate(R.id.aboutAppFragment)
        }
        return true
    }

    override fun onBackPressed() {
        findNavController(R.id.fragmentContainer).popBackStack()
        //findNavController(R.id.fragmentContainer).popBackStack(R.id.rootRandomCatsFragment, false)
    }
}