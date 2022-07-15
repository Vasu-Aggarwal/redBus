package com.example.redbus.ui.seats

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.example.redbus.R
import com.example.redbus.ui.payment.paymentMethodActivity
import com.example.redbus.ui.registration.RegisterUser
import com.example.redbus.utilities.Constants
import com.example.redbus.utilities.SharedPref
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_book_seats.*
import kotlinx.android.synthetic.main.activity_contact_info.*
import kotlinx.android.synthetic.main.activity_contact_info.txtPrice

class contactInfo : AppCompatActivity() {

    private var seats = ArrayList<String>()
    private var size: Int = 0
    private var price: String? = null
    private lateinit var mdialog: Dialog
    private var payment_details = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
        supportActionBar?.hide()

        //Get all the seats selected from previous activity - bookSeats
        val bundle = intent.extras!!
        seats = bundle.getStringArrayList("seats")!!
        price = bundle.getString("price")!!

        //find the number of seats to show the card view accordingly
        seats.forEach {
            size++
        }

        if(size == 1){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }
        }

        if(size == 2){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    Toast.makeText(this, "you clicked 1", Toast.LENGTH_SHORT).show()
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }

            rg2.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbMale2.text.toString())
                if (checkedId == R.id.rbFemale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbFemale2.text.toString())
            }
        }

        if(size == 3){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }

            rg2.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbMale2.text.toString())
                if (checkedId == R.id.rbFemale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbFemale2.text.toString())
            }

            rg3.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbMale3.text.toString())
                if (checkedId == R.id.rbFemale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbFemale3.text.toString())
            }
        }

        if(size == 4){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }

            rg2.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbMale2.text.toString())
                if (checkedId == R.id.rbFemale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbFemale2.text.toString())
            }

            rg3.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbMale3.text.toString())
                if (checkedId == R.id.rbFemale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbFemale3.text.toString())
            }

            rg4.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbMale4.text.toString())
                if (checkedId == R.id.rbFemale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbFemale4.text.toString())
            }
        }

        if(size == 5){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }

            rg2.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbMale2.text.toString())
                if (checkedId == R.id.rbFemale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbFemale2.text.toString())
            }

            rg3.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbMale3.text.toString())
                if (checkedId == R.id.rbFemale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbFemale3.text.toString())
            }

            rg4.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbMale4.text.toString())
                if (checkedId == R.id.rbFemale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbFemale4.text.toString())
            }

            rg5.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale5)
                    SharedPref(this).setString(Constants.USER_GENDER5, rbMale5.text.toString())
                if (checkedId == R.id.rbFemale5)
                    SharedPref(this).setString(Constants.USER_GENDER5, rbFemale5.text.toString())
            }
        }

        if(size == 6){
            rg1.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbMale1.text.toString())
                if (checkedId == R.id.rbFemale1)
                    SharedPref(this).setString(Constants.USER_GENDER1, rbFemale1.text.toString())
            }

            rg2.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbMale2.text.toString())
                if (checkedId == R.id.rbFemale2)
                    SharedPref(this).setString(Constants.USER_GENDER2, rbFemale2.text.toString())
            }

            rg3.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbMale3.text.toString())
                if (checkedId == R.id.rbFemale3)
                    SharedPref(this).setString(Constants.USER_GENDER3, rbFemale3.text.toString())
            }

            rg4.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbMale4.text.toString())
                if (checkedId == R.id.rbFemale4)
                    SharedPref(this).setString(Constants.USER_GENDER4, rbFemale4.text.toString())
            }

            rg5.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale5)
                    SharedPref(this).setString(Constants.USER_GENDER5, rbMale5.text.toString())
                if (checkedId == R.id.rbFemale5)
                    SharedPref(this).setString(Constants.USER_GENDER5, rbFemale5.text.toString())
            }

            rg6.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbMale6)
                    SharedPref(this).setString(Constants.USER_GENDER6, rbMale6.text.toString())
                if (checkedId == R.id.rbFemale6)
                    SharedPref(this).setString(Constants.USER_GENDER6, rbFemale6.text.toString())
            }
        }

        btnMore.setOnClickListener {

            mdialog = Dialog(this)
            mdialog.setContentView(R.layout.more_info)
            mdialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mdialog.window?.setLayout(1000, 100)
            mdialog.window?.setGravity(Gravity.BOTTOM)
            mdialog.window?.attributes?.y = 205

            val txtTotalSeats = mdialog.window?.findViewById<TextView>(R.id.txtTotalSeats)
            val txtTotalPrice = mdialog.window?.findViewById<TextView>(R.id.txtTotalPrice)
            val txtSeatIDs = mdialog.window?.findViewById<TextView>(R.id.txtSeatIDs)
            var currSize = 0

            txtSeatIDs?.text = ""
            seats.forEach {
                currSize++
                if(currSize == size)
                    txtSeatIDs?.text = txtSeatIDs?.text.toString()+ it
                else
                    txtSeatIDs?.text = txtSeatIDs?.text.toString()+ it +", "
            }

            if(size == 1)
                txtTotalSeats?.text = "$size Seat "
            else
                txtTotalSeats?.text = "$size Seats "

            txtTotalPrice?.text = "₹ $price"

            mdialog.setOnCancelListener {
                mdialog.dismiss()
            }

            mdialog.show()
        }

        btnProceed.setOnClickListener {

            val userID = SharedPref(this).getString(Constants.USER_ID)
            Log.d("userid", "onCreate: "+userID)

            val ccp = findViewById<CountryCodePicker>(R.id.ccp)
            val etPhone = findViewById<EditText>(R.id.etPhone)
            ccp.registerCarrierNumberEditText(etPhone)

            //initialize the payment_details array
            payment_details.add(price!!)    //add amount
            payment_details.add(etEmail.text.toString())    //add email
            payment_details.add(ccp.fullNumberWithPlus.trim())  //add phone number
            payment_details.add(SharedPref(this).getString(Constants.USER_ID)!!)    //add User ID

            if(userID == "user_id" || userID == ""){
                val intent = Intent(this, RegisterUser::class.java)
                intent.putExtra("number", ccp.fullNumberWithPlus.trim())
                startActivity(intent)
            }
            else{
                val intent = Intent(this, paymentMethodActivity::class.java)
                intent.putExtra("payment_details", payment_details)
                intent.putExtra("size", size)
                intent.putExtra("seatID", seats)
                startActivity(intent)
            }

            if(size == 1)
                sendData1()

            if(size == 2)
                sendData2()
        }

        setView(size)
        txtPrice.text = price
    }

    private fun setView(size: Int) {
        val cv2 = findViewById<CardView>(R.id.cvp2)
        val cv3 = findViewById<CardView>(R.id.cvp3)
        val cv4 = findViewById<CardView>(R.id.cvp4)
        val cv5 = findViewById<CardView>(R.id.cvp5)
        val cv6 = findViewById<CardView>(R.id.cvp6)

        if(size == 1){
            seatID1.text = "Seat " + seats[0]
        }

        if (size == 2) {
            seatID1.text = "Seat " + seats[0]
            seatID2.text = "Seat " + seats[1]
            cv2.visibility = View.VISIBLE
        }

        if (size == 3) {
            seatID1.text = "Seat " + seats[0]
            seatID2.text = "Seat " + seats[1]
            seatID3.text = "Seat " + seats[2]
            cv2.visibility = View.VISIBLE
            cv3.visibility = View.VISIBLE
        }

        if (size == 4) {
            seatID1.text = "Seat " + seats[0]
            seatID2.text = "Seat " + seats[1]
            seatID3.text = "Seat " + seats[2]
            seatID4.text = "Seat " + seats[3]
            cv2.visibility = View.VISIBLE
            cv3.visibility = View.VISIBLE
            cv4.visibility = View.VISIBLE
        }

        if (size == 5) {
            seatID1.text = "Seat " + seats[0]
            seatID2.text = "Seat " + seats[1]
            seatID3.text = "Seat " + seats[2]
            seatID4.text = "Seat " + seats[3]
            seatID5.text = "Seat " + seats[4]
            cv2.visibility = View.VISIBLE
            cv3.visibility = View.VISIBLE
            cv4.visibility = View.VISIBLE
            cv5.visibility = View.VISIBLE
        }

        if (size == 6) {
            seatID1.text = "Seat " + seats[0]
            seatID2.text = "Seat " + seats[1]
            seatID3.text = "Seat " + seats[2]
            seatID4.text = "Seat " + seats[3]
            seatID5.text = "Seat " + seats[4]
            seatID6.text = "Seat " + seats[5]
            cv2.visibility = View.VISIBLE
            cv3.visibility = View.VISIBLE
            cv4.visibility = View.VISIBLE
            cv5.visibility = View.VISIBLE
            cv6.visibility = View.VISIBLE
        }
    }

    // All the sendData functions are used to send info to the SharedPref



//    private fun seterror(etName: Int, etage: Int, etGender: Int) {
//        val name = findViewById<EditText>(etName)
//        val age = findViewById<EditText>(etage)
//        val gender = findViewById<RadioGroup>(etGender)
//
//        if(name.text?.isEmpty()!!)
//            name.setError("Name is not valid")
//        if(age.text?.isEmpty()!!)
//            age.setError("Age is not valid")
//
//        gender.setOnCheckedChangeListener { group, checkedId ->
//            if(checkedId == -1){
//                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
private fun setRadioButton(rg1: Int, rb1: Int, rb2: Int, userGender1: String){

}
    private fun sendData1(){
        SharedPref(this).setString(Constants.USER_EMAIL, etEmail.text.toString())
        SharedPref(this).setString(Constants.USER_PHONE, etPhone.text.toString())
        SharedPref(this).setString(Constants.USER_NAME1, etName1.text.toString())
        SharedPref(this).setString(Constants.USER_AGE1, age1.text.toString())
    }


    private fun sendData2() {
        sendData1()
        SharedPref(this).setString(Constants.USER_NAME2, etName2.text.toString())
        SharedPref(this).setString(Constants.USER_AGE2, age2.text.toString())
    }

    private fun sendData3() {
        sendData1()
        sendData2()
        SharedPref(this).setString(Constants.USER_NAME3, etName3.text.toString())
        setRadioButton(R.id.rg3, R.id.rbMale3, R.id.rbFemale3, Constants.USER_GENDER3)
        SharedPref(this).setString(Constants.USER_AGE3, age3.text.toString())
    }

    private fun sendData4() {
        sendData1()
        sendData2()
        sendData3()
        SharedPref(this).setString(Constants.USER_NAME4, etName4.text.toString())
        setRadioButton(R.id.rg4, R.id.rbMale4, R.id.rbFemale4, Constants.USER_GENDER4)
        SharedPref(this).setString(Constants.USER_AGE4, age4.text.toString())
    }

    private fun sendData5() {
        sendData1()
        sendData2()
        sendData3()
        sendData4()
        SharedPref(this).setString(Constants.USER_NAME5, etName5.text.toString())
        setRadioButton(R.id.rg5, R.id.rbMale5, R.id.rbFemale5, Constants.USER_GENDER5)
        SharedPref(this).setString(Constants.USER_AGE5, age5.text.toString())
    }

    private fun sendData6() {
        sendData1()
        sendData2()
        sendData3()
        sendData4()
        sendData5()
        SharedPref(this).setString(Constants.USER_NAME6, etName6.text.toString())
        setRadioButton(R.id.rg6, R.id.rbMale6, R.id.rbFemale6, Constants.USER_GENDER6)
        SharedPref(this).setString(Constants.USER_AGE6, age6.text.toString())
    }

}