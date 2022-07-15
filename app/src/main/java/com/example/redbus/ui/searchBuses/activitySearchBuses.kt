package com.example.redbus.ui.searchBuses

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redbus.R
import com.example.redbus.models.DataItemModel
import com.example.redbus.ui.searchBuses.adapter.myAdapter
import com.example.redbus.ui.seats.bookSeats
import com.example.redbus.utilities.Constants
import com.example.redbus.utilities.SharedPref
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_search_buses.*
import kotlinx.android.synthetic.main.activity_search_buses.txtDestLoc
import kotlinx.android.synthetic.main.activity_search_buses.txtSourceLoc
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class activitySearchBuses : AppCompatActivity(), myAdapter.ItemClickListener {

    private val documentDataList: MutableList<DataItemModel> = ArrayList()
    private lateinit var dataItemModel: MutableList<DataItemModel>
    val db = Firebase.firestore
    var myAdapter: myAdapter ?= null
    private lateinit var dbLoc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_buses)
        supportActionBar!!.hide()

        val src = intent.getStringExtra("src")
        val dest = intent.getStringExtra("dest")
        val date = intent.getStringExtra("date")

        val outDateFormat = SimpleDateFormat("dd-MM-2022")
        val inputDateFormat = SimpleDateFormat("EEE, d MMM")

        val date1 = inputDateFormat.parse(date)
        val out = outDateFormat.format(date1)   //to get the buses on desired date

        txtdate.text = date
        txtSourceLoc.text = src
        txtDestLoc.text = dest
        dbLoc = src+dest        //to search in the db
        rcView.layoutManager = LinearLayoutManager(this)
        getBuses(dbLoc, out)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    private fun getBuses(key: String, date: String) {
        db.collection("Buslist").document(key).collection("buses")
            .whereEqualTo("travel_date", date) .get().addOnSuccessListener {
            dataItemModel = it.toObjects(DataItemModel::class.java)
            documentDataList.addAll(dataItemModel)
                Toast.makeText(this, "${documentDataList.count()} buses found", Toast.LENGTH_SHORT).show()
                setAdapter()

                if(documentDataList.size == 0){
                    finish()
                }
        }
    }

    private fun setAdapter() {
        if(myAdapter != null){
            myAdapter?.updateList(documentDataList)
            rcView.adapter = myAdapter
        }
        else{
            myAdapter = myAdapter(documentDataList, this)
            rcView.adapter = myAdapter
        }
    }

    override fun onItemClick(position: Int) {

        val intent = Intent(this, bookSeats::class.java)
        val click = position+1
        SharedPref(this).setString(Constants.CLICK, click.toString())
        intent.putExtra("busid", documentDataList.get(position).id)
        intent.putExtra("key", documentDataList.get(position).key)
        startActivity(intent)
        finish()
    }

}