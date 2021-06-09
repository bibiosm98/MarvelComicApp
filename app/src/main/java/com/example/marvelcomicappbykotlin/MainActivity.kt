package com.example.marvelcomicappbykotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_search_black_24dp)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment) {
//                navView.visibility = View.GONE
//                supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//                supportActionBar?.setCustomView(R.layout.detail_title)

            } else {
//                navView.visibility = View.VISIBLE
//                supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_TITLE
//                actionBar?.setHomeAsUpIndicator(0)
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else ->{
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}