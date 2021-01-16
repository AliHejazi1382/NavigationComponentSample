package com.example.navigationcomponentsample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.extensions.setupWithNavController
import com.example.navigationcomponentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNav(this)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNav(this)
    }

    private fun setupBottomNav(lifecycleOwner: LifecycleOwner) {
        binding.apply {

            val navGraphIds = listOf(
                R.navigation.home,
                R.navigation.list,
                R.navigation.user
            )

            val controller = bottomNav.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.fragment_container,
                intent = intent
            )

            controller.observe(lifecycleOwner, { navController ->
                setupActionBarWithNavController(navController)
            })

            currentNavController = controller
        }
    }

    override fun onDestroy() {
        currentNavController = null
        _binding = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}