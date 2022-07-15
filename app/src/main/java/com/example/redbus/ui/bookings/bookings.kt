package com.example.redbus.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.redbus.R
import com.example.redbus.models.DataItemModel
import com.example.redbus.models.UserItemModel
import com.example.redbus.ui.bookings.adapter.bookingAdapter
import com.example.redbus.utilities.Constants
import com.example.redbus.utilities.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_bookings.*

class bookings : Fragment(), bookingAdapter.ItemClickListener {

    private val documentDataList: MutableList<UserItemModel> = ArrayList()
    private lateinit var userItemModel: MutableList<UserItemModel>
    val db = FirebaseFirestore.getInstance()
    var bookingAdapter: bookingAdapter ?= null
//    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_bookings, container, false)
        return rootView

//        swipeRefreshLayout.isRefreshing = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvBookings.layoutManager = LinearLayoutManager(context)
        getBookings()
    }

    private fun getBookings() {
        db.collection("UserDetails").document(SharedPref(activity!!)
            .getString(Constants.USER_ID).toString()).collection("bookings")
            .get().addOnSuccessListener {
                userItemModel = it.toObjects(UserItemModel::class.java)
                documentDataList.addAll(userItemModel)
                setAdapter()
            }
    }

    private fun setAdapter() {
        if(bookingAdapter != null){
            bookingAdapter?.updateList(documentDataList)
            rvBookings.adapter = bookingAdapter
        }
        else {
            bookingAdapter = bookingAdapter(userItemModel, this)
            rvBookings.adapter = bookingAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()

    }
}