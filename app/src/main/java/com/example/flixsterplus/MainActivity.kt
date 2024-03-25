package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.BuildConfig
import com.example.flixsterplus.R.id
import com.example.flixsterplus.BuildConfig.FLIXSTER_API_KEY
import com.example.flixsterplus.databinding.ActivityMainBinding

private const val API_KEY = FLIXSTER_API_KEY


class MainActivity : AppCompatActivity() {
    private lateinit var starRecyclerView:RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val stars = mutableListOf<Star>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "FlixsterPlus"
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.content, FragmentStarslist(), null).commit()
    }
}

