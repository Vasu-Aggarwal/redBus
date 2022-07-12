package com.example.redbus.ui.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.redbus.R
import com.example.redbus.databinding.FragmentHomeBinding
import com.example.redbus.models.viewModel
import com.example.redbus.models.viewModelDest
import com.example.redbus.ui.searchBuses.activitySearchBuses
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val viewModel: viewModel by activityViewModels()
    private val viewModelDest: viewModelDest by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateCalendar(calendar)
        }

        txtTom.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 1)
                updateCalendar(calendar)
        }

        txtToday.setOnClickListener {
            val calendar = Calendar.getInstance()
            updateCalendar(calendar)
        }

        val cities = resources.getStringArray(R.array.cities)
        val arrayAdapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, cities)

        txtSource.setAdapter(arrayAdapter)
        txtDest.setAdapter(arrayAdapter)

//        txtSource.setOnClickListener {
//            val intent = Intent(activity!!, activitySearchBuses::class.java)
//            startActivity(intent)
//        }
//
//            viewModel.selectedItem.observe(viewLifecycleOwner) {
//                txtSource.text = it
//            }
//
//        txtDest.setOnClickListener {
//            val intent = Intent(activity!!, activitySearchBusesDest::class.java)
//            startActivity(intent)
//        }
//
//            viewModelDest.selectedItem.observe(viewLifecycleOwner) {
//                txtDest.text = it
//            }

        updateCalendar(calendar)

        txtdate.setOnClickListener {DatePickerDialog(activity!!, datePicker, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show() }

        btnSearch.setOnClickListener {
            val intent = Intent(activity!!, activitySearchBuses::class.java)
            intent.putExtra("src", txtSource.text.toString())
            intent.putExtra("dest", txtDest.text.toString())
            intent.putExtra("date", txtdate.text.toString())
            startActivity(intent)
        }

    }

    private fun updateCalendar(calendar: Calendar) {
            val simpleDateFormat = SimpleDateFormat("EEE, d MMM")
            val date = simpleDateFormat.format(calendar.time)
            txtdate.text = date
        }
}


