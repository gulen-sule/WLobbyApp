package com.example.wlobbyapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.wlobbyapp.R
import com.example.wlobbyapp.data.database.room.repository.WatchedItemRepository
import com.example.wlobbyapp.data.event.MessageEvent
import com.example.wlobbyapp.data.event.UploadServiceEvent
import com.example.wlobbyapp.service.PosterImageService
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
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

        startService(Intent(baseContext, PosterImageService::class.java))

        CoroutineScope(Dispatchers.Main).launch {
            val data = watchedItemRepository.getWatchedAll(applicationContext)
            //  Log.d("jsonDATA", Gson().toJson(data))
        }

        setBottomNavigation()

        val eventBus = EventBus.getDefault()
        eventBus.register(this)
    }

//    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
//        replace(R.id.fragmentContainerView, fragment)
//        commit()
//    } Navigation componenti eziyor

    private fun setupNavigation() {//neden bundan onceye koydugumda calismiyor
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

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

    @Subscribe
    fun onEvent(event: Any) {
        Handler(Looper.getMainLooper()).post {
            when (event) {
                is UploadServiceEvent -> {
                    Log.d("eventTAG", event.value.toString())
                    //binding.textView2.text = event.value.toString()
                }
                is MessageEvent -> {
                    Toast.makeText(this.baseContext, event.getMessage().toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}