package com.example.wlobbyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.room.repository.WatchedItemRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var watchedItemRepository: WatchedItemRepository

    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bottomNavigation = findViewById(R.id.bottomNavigation)

        CoroutineScope(Dispatchers.Main).launch {
            val data = watchedItemRepository.getWatchedAll(applicationContext)
            Log.d("jsonDATA", Gson().toJson(data))
        }

        //setCurrentFragment(LobbyMainFragment())
        setupNavigation()
        setUpActionBar()

        bottomNavigation.setOnNavigationItemSelectedListener {
            Log.d("item_id_check", it.itemId.toString())
            when (it.itemId) {
                R.id.homeMenuItem -> {
                    //setCurrentFragment(LobbyMainFragment())
                    navController.navigate(R.id.lobbyMainFragment)
                }
                R.id.homeAddItem -> {
                    navController.navigate(R.id.chooseDateFragment)
                    //setCurrentFragment(SearchResultsFragment())
                }
                R.id.homeSearchItem -> {
                    navController.navigate(R.id.searchResultsFragment)
                    //setCurrentFragment(SearchResultsFragment())
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragmentContainerView, fragment)
        commit()
    }

    private fun setupNavigation() {
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    private fun setUpActionBar() {
        appBarConfiguration = AppBarConfiguration.Builder().build()
        appBarConfiguration.topLevelDestinations
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}