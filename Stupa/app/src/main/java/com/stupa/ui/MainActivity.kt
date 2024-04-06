package com.stupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.stupa.R
import com.stupa.base.BaseActivity
import com.stupa.constants.Constants
import com.stupa.databinding.ActivityMainBinding
import com.stupa.userDetails.UserDetailsFragmentDirections
import com.stupa.util.visibilityGone
import com.stupa.util.visible


class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var navGraph: NavGraph

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // adding onbackpressed callback listener.
//        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(binding.toolbar)


        val navHost =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHost.navController
        val a = navController.currentBackStack.value
        Log.d("hsbdshbj", "onCreate: $a")
        val graphInflater = navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.navigation_graph)


        val destination = if (sharedPreference.getString(Constants.USER_NAME).isNullOrEmpty()) {
            R.id.loginFragment
        } else {
            R.id.userDetailsFragment
        }
        navGraph.setStartDestination(destination)
        navController.graph = navGraph
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { contrkoller, destination, arguments ->
            val id = destination.id
            when (id) {
                R.id.userDetailsFragment -> {
                    toolbar.visible()
                    binding.toolbar.navigationIcon = null
                }
                R.id.registerFragment -> toolbar.visibilityGone()
                R.id.loginFragment -> toolbar.visibilityGone()
                else -> toolbar.visible()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.title) {
            getString(R.string.logout) -> {
                sharedPreference.clearPreference()
                val nav = UserDetailsFragmentDirections.actionUserDetailsFragmentToLoginFragment()
                navController.navigate(nav)
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }

            else -> {
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }
        }
    }
//
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }

//    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            if (navController.currentDestination!!.id == R.id.loginFragment) {
//                finish()
//            }else{
//              onBackPressedDispatcher.onBackPressed()
//            }
//        }
//    }

}