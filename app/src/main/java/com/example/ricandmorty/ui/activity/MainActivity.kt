package com.example.ricandmorty.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ricandmorty.R
import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.data.remote.ApiService
import com.example.ricandmorty.data.remote.getClient
import com.example.ricandmorty.databinding.ActivityMainBinding
import com.example.ricandmorty.ui.fragmet.FragmentAdapter
import com.example.ricandmorty.ui.fragmet.characterfrgagment.CharacterFragment
import com.example.ricandmorty.ui.fragmet.episodefragment.EpisodeFragment
import com.example.ricandmorty.ui.fragmet.locationfragment.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupNavigation()

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        binding.refresh
        fragmentAdapter.addFragment(CharacterFragment(), "Character")
        fragmentAdapter.addFragment(EpisodeFragment(), "Episodes")
        fragmentAdapter.addFragment(LocationFragment(), "Location")

        binding.viewPager.adapter = fragmentAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!! as NavHostFragment
        navController = navHostFragment.navController
    }
}