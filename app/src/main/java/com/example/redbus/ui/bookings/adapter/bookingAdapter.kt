package com.example.redbus.ui.bookings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redbus.R
import com.example.redbus.models.UserItemModel
import java.text.SimpleDateFormat
import java.util.*

class bookingAdapter (var userItemModel: MutableList<UserItemModel>, var mClickListener: ItemClickListener)
    : RecyclerView.Adapter<bookingAdapter.ViewHolder>(){

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtSourceLoc = itemView.findViewById<TextView>(R.id.txtSourceLoc)
        val txtDestLoc = itemView.findViewById<TextView>(R.id.txtDestLoc)
        val txtBusTimingDeparture = itemView.findViewById<TextView>(R.id.txtBusTimingDeparture)
        val txtBusTimingArrival = itemView.findViewById<TextView>(R.id.txtBusTimingArrival)
        val txtTravelDate = itemView.findViewById<TextView>(R.id.txtTravelDate)
        val txtCompany = itemView.findViewById<TextView>(R.id.txtCompany)
        val txtSeatIDs = itemView.findViewById<TextView>(R.id.txtSeatIDs)
        val txtWeek = itemView.findViewById<TextView>(R.id.txtWeek)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.adapter_my_bookings, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtBusTimingArrival.setText(userItemModel.get(position).arrival_time)
        holder.txtBusTimingDeparture.setText(userItemModel.get(position).departure_time)
        holder.txtCompany.setText(userItemModel.get(position).bus_company)
        holder.txtDestLoc.setText(userItemModel.get(position).destination_location)
        holder.txtSourceLoc.setText(userItemModel.get(position).source_location)
        holder.txtTravelDate.setText(userItemModel.get(position).travel_date)
        holder.txtSeatIDs.setText(userItemModel.get(position).seatid1)
        val sdf = SimpleDateFormat("EEEE")
        val date = Date()
        holder.txtWeek.setText(sdf.format(date))
        holder.itemView.setOnClickListener {
            mClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return userItemModel.size
    }

    fun updateList(temp: MutableList<UserItemModel>) {
        userItemModel = temp
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onItemClick(position: Int)
    }
}

