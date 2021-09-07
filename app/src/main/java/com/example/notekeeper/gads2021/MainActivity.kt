package com.example.notekeeper.gads2021

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notekeeper.gads2021.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navigationView: NavigationView = binding.navView
        drawerLayout = binding.drawerLayout
        // Get NavHostFragment and NavController
        val navHostFrag =
            supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment_content_main
            ) as NavHostFragment
        val navController = navHostFrag.navController
        // Define AppBarConfiguration: Connect DrawerLayout with Navigation Component
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.NoteListFragment, R.id.NoteFragment), drawerLayout
        )
        // Connect Toolbar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Connect NavigationView with NavController
        navigationView.setupWithNavController(navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen) {
            drawerLayout.close()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_notes -> {
                handleSelection("Notes")
            }
            R.id.nav_courses -> {
                handleSelection("Courses")
            }
            R.id.nav_share -> {
                handleSelection("Don't you think you have shared enough")
            }
            R.id.nav_send -> {
                handleSelection("Send")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(binding.navView, message, Snackbar.LENGTH_SHORT).show()
    }
}