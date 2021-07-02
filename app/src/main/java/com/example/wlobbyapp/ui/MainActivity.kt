package com.example.wlobbyapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.WatchedItemRepository
import com.example.wlobbyapp.services.PosterImageService
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var watchedItemRepository: WatchedItemRepository//ihtyac yok aslinda

    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        startService(Intent(baseContext, PosterImageService::class.java))

        setBottomNavigation()
    }

//    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
//        replace(R.id.fragmentContainerView, fragment)
//        commit()
//    } Navigation componenti eziyor

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation)

        setupNavigation()

        bottomNavigation.setOnNavigationItemSelectedListener {
            Log.d("item_id_check", it.itemId.toString())
            when (it.itemId) {
                R.id.homeMenuItem -> {
                    //setCurrentFragment(LobbyMainFragment())
                    navController.navigate(R.id.lobbyMainFragment)
                }
                R.id.profilePageItem -> {
                    navController.navigate(R.id.profileMainFragment)
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

    private fun setupNavigation() {//neden bundan onceye koydugumda calismiyor
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

//    @Subscribe
//    fun onEvent(event: Any) {
//        Handler(Looper.getMainLooper()).post {
//            when (event) {
//                is UploadServiceEvent -> {
//                    Log.d("eventTAG", event.value.toString())
//                    //binding.textView2.text = event.value.toString()
//                }
//                is MessageEvent -> {
//                    Toast.makeText(this.baseContext, event.getMessage().toString(), Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }

}