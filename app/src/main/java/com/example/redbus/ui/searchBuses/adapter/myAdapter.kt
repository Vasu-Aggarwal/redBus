package com.example.redbus.ui.searchBuses.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redbus.R
import com.example.redbus.models.DataItemModel
import kotlin.reflect.typeOf

class myAdapter(var dataItemModel: MutableList<DataItemModel>, var mClickListener: ItemClickListener)
    : RecyclerView.Adapter<myAdapter.ViewHolder>(){

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtBusTimingDeparture = itemView.findViewById<TextView>(R.id.txtBusTimingDeparture)
        val txtBusTimingArrival = itemView.findViewById<TextView>(R.id.txtBusTimingArrival)
        val txtAvailableSeats = itemView.findViewById<TextView>(R.id.txtAvailableSeats)
        val txtCompany = itemView.findViewById<TextView>(R.id.txtCompany)
        val txtPrice = itemView.findViewById<TextView>(R.id.txtPrice)
        val txtRating = itemView.findViewById<TextView>(R.id.txtRating)
        val txtSourceLoc = itemView.findViewById<TextView>(R.id.txtSourceLoc)
        val txtDestLoc = itemView.findViewById<TextView>(R.id.txtDestLoc)
        val txtTotalRating = itemView.findViewById<TextView>(R.id.txtTotalRating)
        val txtAvailableSeatsss = itemView.findViewById<TextView>(R.id.txtAvailableSeatsss)
        val txtBusName = itemView.findViewById<TextView>(R.id.txtBusName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.adapter_search_buses, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtBusTimingArrival.setText(dataItemModel.get(position).bus_timing_arrival)
        holder.txtBusTimingDeparture.setText(dataItemModel.get(position).bus_timing_departure)
        holder.txtCompany.setText(dataItemModel.get(position).company)
        val ts = dataItemModel.get(position).total_seats.toInt()
        val fos = dataItemModel.get(position).female_occupied_seats.size
        val mos = dataItemModel.get(position).male_occupied_seats.size
        val result = ts - (fos + mos)

        if(result<=10){
            holder.txtAvailableSeats.setTextColor(Color.parseColor("#D6730A"))
            holder.txtAvailableSeats.setTypeface(null, Typeface.BOLD)
            holder.txtAvailableSeatsss.setTextColor(Color.parseColor("#D6730A"))
            holder.txtAvailableSeatsss.setTypeface(null, Typeface.BOLD)
        }

        if(result<=5){
            holder.txtAvailableSeats.setTextColor(Color.RED)
            holder.txtAvailableSeats.setTypeface(null, Typeface.BOLD)
            holder.txtAvailableSeatsss.setTextColor(Color.RED)
            holder.txtAvailableSeatsss.setTypeface(null, Typeface.BOLD)
        }

        holder.txtBusName.setText(dataItemModel.get(position).bus_name)
        holder.txtAvailableSeats.text = result.toString()
        holder.txtDestLoc.setText(dataItemModel.get(position).dropoff_location)
        holder.txtSourceLoc.setText(dataItemModel.get(position).pickup_location)
        holder.txtPrice.setText(dataItemModel.get(position).price)
        holder.txtRating.setText(dataItemModel.get(position).rating)
        holder.txtTotalRating.setText(dataItemModel.get(position).total_rating)
        holder.itemView.setOnClickListener {
            mClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataItemModel.size
    }

    fun updateList(documentDataList: MutableList<DataItemModel>) {
        dataItemModel = documentDataList
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}


