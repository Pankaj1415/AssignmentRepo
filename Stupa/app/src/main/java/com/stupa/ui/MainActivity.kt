package com.stupa.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.stupa.R
import com.stupa.base.BaseActivity
import com.stupa.constants.Constants
import com.stupa.databinding.ActivityMainBinding
import com.stupa.util.visibilityGone
import com.stupa.util.visible


class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var navGraph: NavGraph
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(binding.toolbar)


        val navHost = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHost

        navController = navHost.navController

        val graphInflater = navHost.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.navigation_graph)
        navController = navHost.navController

        val destination = if (sharedPreference.getString(Constants.USER_NAME).isNullOrEmpty()) {
            R.id.loginFragment
        } else {
            R.id.userDetailsFragment
        }
        navGraph.setStartDestination(destination)

        navHost.navController.graph = navGraph
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { contrkoller, destination, arguments ->
            val id = destination.id
            when (id) {
                R.id.registerFragment -> toolbar.visibilityGone()
                R.id.loginFragment -> toolbar.visibilityGone()
                else -> toolbar.visible()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }


}