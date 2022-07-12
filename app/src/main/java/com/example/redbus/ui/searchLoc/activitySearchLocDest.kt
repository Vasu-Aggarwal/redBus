package com.example.redbus.ui.searchLoc

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.redbus.MainActivity
import com.example.redbus.R

class activitySearchLocDest: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)
        supportActionBar?.hide()    //hide the action bar

        val listView = findViewById<ListView>(R.id.lvCities)
        val searchView = findViewById<SearchView>(R.id.searchView)

        val cities = arrayOf("Delhi", "Lucknow", "Kolkata")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val result = cities[i]
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("result1", result)
            startActivity(intent)
            finish()
        }
    }
}
