package com.armin.hw14

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.armin.hw14.databinding.ActivityMainBinding
import com.armin.hw14.ui.adapter.PhotoAdapter
import com.armin.hw14.ui.fragments.search.SearchFragment
import com.armin.hw14.ui.fragments.show.ShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val showFragment = ShowFragment()
    private val searchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationItemSelect()
        replaceFragment(showFragment)

    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        Log.d("H14 Debug", "Start MAinActivity")
        transaction.commit()
    }

    private fun bottomNavigationItemSelect() {
        val bottomNavigatonView: BottomNavigationView = findViewById(R.id.bottomNavigatonView)
        bottomNavigatonView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miShowPhoto -> replaceFragment(showFragment)
                R.id.miSearchPhoto -> replaceFragment(searchFragment)
            }
            true
        }
    }
}