package com.mrvk.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrvk.rickandmorty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentTransaction()
    }

    private fun fragmentTransaction() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.container_main, CharacterListFragment())
        transaction.commit()
    }
}