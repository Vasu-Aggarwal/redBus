package com.example.redbus.ui.payment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.redbus.MainActivity
import com.example.redbus.R
import com.example.redbus.models.DataItemModel
import com.example.redbus.utilities.Constants
import com.example.redbus.utilities.SharedPref
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_payment_method.*
import org.json.JSONObject
import kotlin.math.log

class paymentMethodActivity : AppCompatActivity(), PaymentResultListener {

    private var payment_details = ArrayList<String>()   //to receive payment details
    private var seats = ArrayList<String>()    //to receive seatIDs
    val db = FirebaseFirestore.getInstance()
    private var size: Int = 0
    private var male_occupied_seats = ArrayList<String>()
    private var female_occupied_seats = ArrayList<String>()
    private val documentDataList: MutableList<DataItemModel> = ArrayList()
    private lateinit var dataItemModel: MutableList<DataItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        supportActionBar?.hide()
        Checkout.preload(applicationContext)

        //receive payment_details
        val bundle = intent.extras
        payment_details = bundle?.getStringArrayList("payment_details")!!
        seats = bundle.getStringArrayList("seatID")!!
        size = bundle.getInt("size")
        getbus(SharedPref(this).getString(Constants.KEY),
            SharedPref(this).getString(Constants.BUS_ID))

        //Start payment just after coming to this activity
        startPayment()

        btnGotoHome.setOnClickListener {
            uploadUserDataToFirebase()
            updateBusDetailsInFirebase()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)     //end all the previous activities
            startActivity(intent)
        }
    }

    private fun updateBusDetailsInFirebase() {
        getGender()

        dataItemModel[0].male_occupied_seats.addAll(male_occupied_seats)
        dataItemModel[0].female_occupied_seats.addAll(female_occupied_seats)

        db.collection("Buslist").document(SharedPref(this).getString(Constants.KEY).toString())
            .collection("buses")
            .document("bus"+SharedPref(this).getString(Constants.CLICK))
            .update("male_occupied_seats", dataItemModel[0].male_occupied_seats,
            "female_occupied_seats", dataItemModel[0].female_occupied_seats)

    }

    private fun getbus(key: String?, busID: String?) {
        db.collection("Buslist").document(key!!).collection("buses")
            .whereEqualTo("id", busID)
            .get().addOnSuccessListener {
                dataItemModel = it.toObjects(DataItemModel::class.java)
                documentDataList.addAll(dataItemModel)
            }
    }

    private fun getGender(){        //store all female and male seats
        seats.forEach {
            if(it == "F3" || it == "G1" || it == "G2" || it == "H1" || it == "H3" || it == "J1"
                || it == "J2" || it == "J5")
                female_occupied_seats.add(it)
            else
                male_occupied_seats.add(it)
        }
    }

    private fun uploadUserDataToFirebase() {

        val booking_details = hashMapOf(
            "booking_amount_paid" to payment_details[0],
            "booking_user_email" to payment_details[1],
            "booking_user_phone" to payment_details[2],
            "source_location" to dataItemModel[0].pickup_location,
            "destination_location" to dataItemModel[0].dropoff_location,
            "departure_time" to dataItemModel[0].bus_timing_departure,
            "arrival_time" to dataItemModel[0].bus_timing_arrival,
            "bus_name" to dataItemModel[0].bus_name,
            "bus_company" to dataItemModel[0].company,
            "travel_date" to dataItemModel[0].travel_date
        )

        db.collection("UserDetails").document(payment_details[3]).collection("bookings")
            .document(payment_details[4]).set(booking_details, SetOptions.merge())

        if(size == 1){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }

        Log.d("help", "uploadUserDataToFirebase: "+SharedPref(this).getString(Constants.USER_NAME1).toString()
        +" "+SharedPref(this).getString(Constants.USER_NAME2).toString())

        if(size == 2){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0],
                "user_name2" to SharedPref(this).getString(Constants.USER_NAME2).toString(),
                "user_gender2" to SharedPref(this).getString(Constants.USER_GENDER2).toString(),
                "user_age2" to SharedPref(this).getString(Constants.USER_AGE2).toString(),
                "user_seatid2" to seats[1]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }

        if(size == 3){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0],
                "user_name2" to SharedPref(this).getString(Constants.USER_NAME2).toString(),
                "user_gender2" to SharedPref(this).getString(Constants.USER_GENDER2).toString(),
                "user_age2" to SharedPref(this).getString(Constants.USER_AGE2).toString(),
                "user_seatid2" to seats[1],
                "user_name3" to SharedPref(this).getString(Constants.USER_NAME3).toString(),
                "user_gender3" to SharedPref(this).getString(Constants.USER_GENDER3).toString(),
                "user_age3" to SharedPref(this).getString(Constants.USER_AGE3).toString(),
                "user_seatid3" to seats[2]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }

        if(size == 4){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0],
                "user_name2" to SharedPref(this).getString(Constants.USER_NAME2).toString(),
                "user_gender2" to SharedPref(this).getString(Constants.USER_GENDER2).toString(),
                "user_age2" to SharedPref(this).getString(Constants.USER_AGE2).toString(),
                "user_seatid2" to seats[1],
                "user_name3" to SharedPref(this).getString(Constants.USER_NAME3).toString(),
                "user_gender3" to SharedPref(this).getString(Constants.USER_GENDER3).toString(),
                "user_age3" to SharedPref(this).getString(Constants.USER_AGE3).toString(),
                "user_seatid3" to seats[2],
                "user_name4" to SharedPref(this).getString(Constants.USER_NAME4).toString(),
                "user_gender4" to SharedPref(this).getString(Constants.USER_GENDER4).toString(),
                "user_age4" to SharedPref(this).getString(Constants.USER_AGE4).toString(),
                "user_seatid4" to seats[3]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }

        if(size == 5){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0],
                "user_name2" to SharedPref(this).getString(Constants.USER_NAME2).toString(),
                "user_gender2" to SharedPref(this).getString(Constants.USER_GENDER2).toString(),
                "user_age2" to SharedPref(this).getString(Constants.USER_AGE2).toString(),
                "user_seatid2" to seats[1],
                "user_name3" to SharedPref(this).getString(Constants.USER_NAME3).toString(),
                "user_gender3" to SharedPref(this).getString(Constants.USER_GENDER3).toString(),
                "user_age3" to SharedPref(this).getString(Constants.USER_AGE3).toString(),
                "user_seatid3" to seats[2],
                "user_name4" to SharedPref(this).getString(Constants.USER_NAME4).toString(),
                "user_gender4" to SharedPref(this).getString(Constants.USER_GENDER4).toString(),
                "user_age4" to SharedPref(this).getString(Constants.USER_AGE4).toString(),
                "user_seatid4" to seats[3],
                "user_name5" to SharedPref(this).getString(Constants.USER_NAME5).toString(),
                "user_gender5" to SharedPref(this).getString(Constants.USER_GENDER5).toString(),
                "user_age5" to SharedPref(this).getString(Constants.USER_AGE5).toString(),
                "user_seatid5" to seats[4]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }

        if(size == 6){
            val user_details = hashMapOf(
                "user_name1" to SharedPref(this).getString(Constants.USER_NAME1).toString(),
                "user_gender1" to SharedPref(this).getString(Constants.USER_GENDER1).toString(),
                "user_age1" to SharedPref(this).getString(Constants.USER_AGE1).toString(),
                "user_seatid1" to seats[0],
                "user_name2" to SharedPref(this).getString(Constants.USER_NAME2).toString(),
                "user_gender2" to SharedPref(this).getString(Constants.USER_GENDER2).toString(),
                "user_age2" to SharedPref(this).getString(Constants.USER_AGE2).toString(),
                "user_seatid2" to seats[1],
                "user_name3" to SharedPref(this).getString(Constants.USER_NAME3).toString(),
                "user_gender3" to SharedPref(this).getString(Constants.USER_GENDER3).toString(),
                "user_age3" to SharedPref(this).getString(Constants.USER_AGE3).toString(),
                "user_seatid3" to seats[2],
                "user_name4" to SharedPref(this).getString(Constants.USER_NAME4).toString(),
                "user_gender4" to SharedPref(this).getString(Constants.USER_GENDER4).toString(),
                "user_age4" to SharedPref(this).getString(Constants.USER_AGE4).toString(),
                "user_seatid4" to seats[3],
                "user_name5" to SharedPref(this).getString(Constants.USER_NAME5).toString(),
                "user_gender5" to SharedPref(this).getString(Constants.USER_GENDER5).toString(),
                "user_age5" to SharedPref(this).getString(Constants.USER_AGE5).toString(),
                "user_seatid5" to seats[4],
                "user_name6" to SharedPref(this).getString(Constants.USER_NAME6).toString(),
                "user_gender6" to SharedPref(this).getString(Constants.USER_GENDER6).toString(),
                "user_age6" to SharedPref(this).getString(Constants.USER_AGE6).toString(),
                "user_seatid6" to seats[5]
            )

            db.collection("UserDetails").document(payment_details[3]).collection("bookings")
                .document(payment_details[4]).set(user_details, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun startPayment() {
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_test_1fDWv80mjZ36CG")

        try {
            val options = JSONObject()
            options.put("name","redBus")
            options.put("description","Bus Booking Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
//            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", payment_details[0].toInt()*100)//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", payment_details[1])
            prefill.put("contact",payment_details[2])

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
//        Toast.makeText(this, "Success Payment, ID: $p0", Toast.LENGTH_SHORT).show()
        txtStatus.text = "Payment Status: Successful"
        payment_details.add(p0.toString())  //add paymentID to be uploaded as unique bookingID
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Failed because of $p1", Toast.LENGTH_SHORT).show()
//        Log.d("rea", "onPaymentError: $p1")
        txtStatus.text = "Payment Status: Failure"
    }
}