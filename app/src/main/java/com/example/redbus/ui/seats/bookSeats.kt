package com.example.redbus.ui.seats


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.redbus.R
import com.example.redbus.models.DataItemModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_book_seats.*
import java.text.SimpleDateFormat
import kotlin.math.absoluteValue

class bookSeats : AppCompatActivity() {

    val db = Firebase.firestore
    private val documentDataList: MutableList<DataItemModel> = ArrayList()
    private lateinit var dataItemModel: MutableList<DataItemModel>

    private var mp = mutableMapOf<ImageView, Boolean>()
    private var totalSelectedSeats: Int = 0
    private lateinit var mdialog: Dialog
    private var seats = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_seats)
        supportActionBar?.hide()

        val key = intent.getStringExtra("key")
        val busID = intent.getStringExtra("busid")

        getbus(key, busID)
        setBottomSheet()

        btnBack.setOnClickListener {
            finish()
        }

        snowflakeBtn.setOnClickListener {
            mdialog = Dialog(this)
            mdialog.setContentView(R.layout.snowflake_dialog)
            mdialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            mdialog.window?.setLayout(1000, 300)
            mdialog.setOnCancelListener {
                mdialog.dismiss()
            }
            mdialog.show()
        }

        infoBtn.setOnClickListener{
            mdialog = Dialog(this)
            mdialog.setContentView(R.layout.info_layout)
//            mdialog.window?.setWindowAnimations(R.style.animationDialog)  //animation not working
            mdialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mdialog.window?.setLayout(1000, 300)
            mdialog.show()
        }
    }

    private fun setBottomSheet() {
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 180
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun getbus(key: String?, busID: String?) {
        db.collection("Buslist").document(key!!).collection("buses")
            .whereEqualTo("id", busID)
            .get().addOnSuccessListener {
                dataItemModel = it.toObjects(DataItemModel::class.java)
                documentDataList.addAll(dataItemModel)
                setAdapter()
            }
    }

    private fun setAdapter() {
        txtBusTimingArrival.text = dataItemModel.get(0).bus_timing_arrival
        txtSourceLoc.text = dataItemModel.get(0).pickup_location
        txtDestLoc.text = dataItemModel.get(0).dropoff_location
        txtCompany.text = dataItemModel.get(0).company
        txtBusTimingDeparture.text = dataItemModel.get(0).bus_timing_departure
        txtRating.text = dataItemModel.get(0).rating
        txtTotalRating.text = dataItemModel.get(0).total_rating

        //set date
        val outDateFormat = SimpleDateFormat("EEE, d MMM")
        val inputDateFormat = SimpleDateFormat("dd-MM-2022")

        val date1 = inputDateFormat.parse(dataItemModel.get(0).travel_date)
        val out = outDateFormat.format(date1!!)
        txtDate.text = out

        //set seats for booked
        setSeatsBooked()
        selectSeatsSuper()

    }

    private fun selectSeatsSuper() {

        //A
        val A1 = findViewById<ImageView>(R.id.A1)
        var rA1 = mp[A1]

        if(rA1 == false){
            A1.setOnClickListener { null }
        }

        else{
            selectSeats(A1, "A1")
        }

        val A2 = findViewById<ImageView>(R.id.A2)
        val rA2 = mp[A2]

        if(rA2 == false){

            A2.setOnClickListener { null }
        }
        else{
            selectSeats(A2, "A2")
        }

        val A3 = findViewById<ImageView>(R.id.A3)
        val rA3 = mp[A3]

        if(rA3 == false){
            A3.setOnClickListener { null }
        }
        else{
            selectSeats(A3, "A3")
        }

        val A4 = findViewById<ImageView>(R.id.A4)
        val rA4 = mp[A4]

        if(rA4 == false){
            A4.setOnClickListener { null }
        }
        else{
            selectSeats(A4, "A4")
        }

        //B
        val B1 = findViewById<ImageView>(R.id.B1)
        var rB1 = mp[B1]

        if(rB1 == false){
            B1.setOnClickListener { null }
        }

        else{
            selectSeats(B1, "B1")
        }

        val B2 = findViewById<ImageView>(R.id.B2)
        val rB2 = mp[B2]

        if(rB2 == false){
            B2.setOnClickListener { null }
        }
        else{
            selectSeats(B2, "B2")
        }

        val B3 = findViewById<ImageView>(R.id.B3)
        val rB3 = mp[B3]

        if(rB3 == false){
            B3.setOnClickListener { null }
        }
        else{
            selectSeats(B3, "B3")
        }

        val B4 = findViewById<ImageView>(R.id.B4)
        val rB4 = mp[B4]

        if(rB4 == false){
            B4.setOnClickListener { null }
        }
        else{
            selectSeats(B4, "B4")
        }

        //C
        val C1 = findViewById<ImageView>(R.id.C1)
        var rC1 = mp[C1]

        if(rC1 == false){
            C1.setOnClickListener { null }
        }

        else{
            selectSeats(C1, "C1")
        }

        val C2 = findViewById<ImageView>(R.id.C2)
        val rC2 = mp[C2]

        if(rC2 == false){
            C2.setOnClickListener { null }
        }
        else{
            selectSeats(C2, "C2")
        }

        val C3 = findViewById<ImageView>(R.id.C3)
        val rC3 = mp[C3]

        if(rC3 == false){
            C3.setOnClickListener { null }
        }
        else{
            selectSeats(C3, "C3")
        }

        val C4 = findViewById<ImageView>(R.id.C4)
        val rC4 = mp[C4]

        if(rC4 == false){
            C4.setOnClickListener { null }
        }
        else{
            selectSeats(C4, "C4")
        }

        //D
        val D1 = findViewById<ImageView>(R.id.D1)
        var rD1 = mp[D1]

        if(rD1 == false){
            D1.setOnClickListener { null }
        }

        else{
            selectSeats(D1, "D1")
        }

        val D2 = findViewById<ImageView>(R.id.D2)
        val rD2 = mp[D2]

        if(rD2 == false){
            D2.setOnClickListener { null }
        }
        else{
            selectSeats(D2, "D2")
        }

        val D3 = findViewById<ImageView>(R.id.D3)
        val rD3 = mp[D3]

        if(rD3 == false){
            D3.setOnClickListener { null }
        }
        else{
            selectSeats(D3, "D3")
        }

        val D4 = findViewById<ImageView>(R.id.D4)
        val rD4 = mp[D4]

        if(rD4 == false){
            D4.setOnClickListener { null }
        }
        else{
            selectSeats(D4, "D4")
        }

        //E
        val E1 = findViewById<ImageView>(R.id.E1)
        var rE1 = mp[E1]

        if(rE1 == false){
            E1.setOnClickListener { null }
        }

        else{
            selectSeats(E1, "E1")
        }

        val E2 = findViewById<ImageView>(R.id.E2)
        val rE2 = mp[E2]

        if(rE2 == false){
            E2.setOnClickListener { null }
        }
        else{
            selectSeats(E2, "E2")
        }

        val E3 = findViewById<ImageView>(R.id.E3)
        val rE3 = mp[E3]

        if(rE3 == false){
            E3.setOnClickListener { null }
        }
        else{
            selectSeats(E3, "E3")
        }

        val E4 = findViewById<ImageView>(R.id.E4)
        val rE4 = mp[E4]

        if(rE4 == false){
            E4.setOnClickListener { null }
        }
        else{
            selectSeats(E4, "E4")
        }

        //F
        val F1 = findViewById<ImageView>(R.id.F1)
        var rF1 = mp[F1]

        if(rF1 == false){
            F1.setOnClickListener { null }
        }

        else{
            selectSeats(F1, "F1")
        }

        val F2 = findViewById<ImageView>(R.id.F2)
        val rF2 = mp[F2]

        if(rF2 == false){
            F2.setOnClickListener { null }
        }
        else{
            selectSeats(F2, "F2")
        }

        val F3 = findViewById<ImageView>(R.id.F3)
        val rF3 = mp[F3]

        if(rF3 == false){
            F3.setOnClickListener { null }
        }
        else{
            selectSeats(F3, "F3")
        }

        val F4 = findViewById<ImageView>(R.id.F4)
        val rF4 = mp[F4]

        if(rF4 == false){
            F4.setOnClickListener { null }
        }
        else{
            selectSeats(F4, "F4")
        }

        //G
        val G1 = findViewById<ImageView>(R.id.G1)
        var rG1 = mp[G1]

        if(rG1 == false){
            G1.setOnClickListener { null }
        }

        else{
            selectSeats(G1, "G1")
        }

        val G2 = findViewById<ImageView>(R.id.G2)
        val rG2 = mp[G2]

        if(rG2 == false){
            G2.setOnClickListener { null }
        }
        else{
            selectSeats(G2, "G2")
        }

        val G3 = findViewById<ImageView>(R.id.G3)
        val rG3 = mp[G3]

        if(rG3 == false){
            G3.setOnClickListener { null }
        }
        else{
            selectSeats(G3, "G3")
        }

        val G4 = findViewById<ImageView>(R.id.G4)
        val rG4 = mp[G4]

        if(rG4 == false){
            G4.setOnClickListener { null }
        }
        else{
            selectSeats(G4, "G4")
        }

        //H
        val H1 = findViewById<ImageView>(R.id.H1)
        var rH1 = mp[H1]

        if(rH1 == false){
            H1.setOnClickListener { null }
        }

        else{
            selectSeats(H1, "H1")
        }

        val H2 = findViewById<ImageView>(R.id.H2)
        val rH2 = mp[H2]

        if(rH2 == false){
            H2.setOnClickListener { null }
        }
        else{
            selectSeats(H2, "H2")
        }

        val H3 = findViewById<ImageView>(R.id.H3)
        val rH3 = mp[H3]

        if(rH3 == false){
            H3.setOnClickListener { null }
        }
        else{
            selectSeats(H3, "H3")
        }

        val H4 = findViewById<ImageView>(R.id.H4)
        val rH4 = mp[H4]

        if(rH4 == false){
            H4.setOnClickListener { null }
        }
        else{
            selectSeats(H4, "H4")
        }

        //I
        val I1 = findViewById<ImageView>(R.id.I1)
        var rI1 = mp[I1]

        if(rI1 == false){
            I1.setOnClickListener { null }
        }

        else{
            selectSeats(I1, "I1")
        }

        val I2 = findViewById<ImageView>(R.id.I2)
        val rI2 = mp[I2]

        if(rI2 == false){
            I2.setOnClickListener { null }
        }
        else{
            selectSeats(I2, "I2")
        }

        val I3 = findViewById<ImageView>(R.id.I3)
        val rI3 = mp[I3]

        if(rI3 == false){
            I3.setOnClickListener { null }
        }
        else{
            selectSeats(I3, "I3")
        }

        val I4 = findViewById<ImageView>(R.id.I4)
        val rI4 = mp[I4]

        if(rI4 == false){
            I4.setOnClickListener { null }
        }
        else{
            selectSeats(I4, "I4")
        }

        //J
        val J1 = findViewById<ImageView>(R.id.J1)
        var rJ1 = mp[J1]

        if(rJ1 == false){
            J1.setOnClickListener { null }
        }

        else{
            selectSeats(J1, "J1")
        }

        val J2 = findViewById<ImageView>(R.id.J2)
        val rJ2 = mp[J2]

        if(rJ2 == false){
            J2.setOnClickListener { null }
        }
        else{
            selectSeats(J2, "J2")
        }

        val J3 = findViewById<ImageView>(R.id.J3)
        val rJ3 = mp[J3]

        if(rJ3 == false){
            J3.setOnClickListener { null }
        }
        else{
            selectSeats(J3, "J3")
        }

        val J4 = findViewById<ImageView>(R.id.J4)
        val rJ4 = mp[J4]

        if(rJ4 == false){
            J4.setOnClickListener { null }
        }
        else{
            selectSeats(J4, "J4")
        }

        val J5 = findViewById<ImageView>(R.id.J5)
        val rJ5 = mp[J5]

        if(rJ5 == false){
            J5.setOnClickListener { null }
        }
        else{
            selectSeats(J5, "J5")
        }
    }

    private fun selectSeats(img: ImageView, s: String) {
        var flag = false
        img.setOnClickListener{

            if(totalSelectedSeats == 6 && !flag){
                Toast.makeText(this, "Maximum 6 seats allowed", Toast.LENGTH_SHORT).show()
            }

            if(!flag && totalSelectedSeats<6) {
                totalSelectedSeats++
                seats.add(s)
                setBottomSheetPrice(flag)
                img.setImageResource(R.drawable.male_selected)
                flag = true
            }
            else if(flag){
                totalSelectedSeats--
                seats.remove(s)
                setBottomSheetPrice(flag)
                img.setImageResource(R.drawable.male_empty)
                flag = false
            }
        }
    }

    private fun setBottomSheetPrice(flag: Boolean) {
        BottomSheetBehavior.from(bottomSheetPrice).apply {
            bottomSheetPrice.visibility = View.VISIBLE
            bottomSheet.visibility = View.GONE
            peekHeight = 180
            this.state = BottomSheetBehavior.STATE_COLLAPSED

            if(totalSelectedSeats == 0){
                bottomSheetPrice.visibility = View.GONE
                bottomSheet.visibility = View.VISIBLE
            }

            if(!flag){
                val price = dataItemModel.get(0).price.toInt()
                txtPrice.text = (price*totalSelectedSeats).toString()
                txtSeats.text = ""
                for(i in seats){
                    txtSeats.text = txtSeats.text.toString()+ i +","
                }
            }
            else if(flag){
                val price = dataItemModel.get(0).price.toInt()
                txtPrice.text = (price*totalSelectedSeats).toString()
                txtSeats.text = ""
                for(i in seats){
                    txtSeats.text = txtSeats.text.toString()+ i +","
                }
            }
        }
    }

    private fun setSeatsBooked() {
        for(i in dataItemModel[0].male_occupied_seats) {

            //A
            if(i == "A1"){
                val img = findViewById<ImageView>(R.id.A1)
                img.setImageResource(R.drawable.male_booked)
                mp[A1] = false
            }

            if(i == "A2"){
                val img = findViewById<ImageView>(R.id.A2)
                img.setImageResource(R.drawable.male_booked)
                mp[A2] = false
            }

            if(i == "A3"){
                val img = findViewById<ImageView>(R.id.A3)
                img.setImageResource(R.drawable.male_booked)
                mp[A3] = false
            }

            if(i == "A4"){
                val img = findViewById<ImageView>(R.id.A4)
                img.setImageResource(R.drawable.male_booked)
                mp[A4] = false
            }

            //B
            if(i == "B1"){
                val img = findViewById<ImageView>(R.id.B1)
                img.setImageResource(R.drawable.male_booked)
                mp[B1] = false
            }

            if(i == "B2"){
                val img = findViewById<ImageView>(R.id.B2)
                img.setImageResource(R.drawable.male_booked)
                mp[B2] = false
            }

            if(i == "B3"){
                val img = findViewById<ImageView>(R.id.B3)
                img.setImageResource(R.drawable.male_booked)
                mp[B3] = false
            }

            if(i == "B4"){
                val img = findViewById<ImageView>(R.id.B4)
                img.setImageResource(R.drawable.male_booked)
                mp[B4] = false
            }

            //C
            if(i == "C1"){
                val img = findViewById<ImageView>(R.id.C1)
                img.setImageResource(R.drawable.male_booked)
                mp[C1] = false
            }

            if(i == "C2"){
                val img = findViewById<ImageView>(R.id.C2)
                img.setImageResource(R.drawable.male_booked)
                mp[C2] = false
            }

            if(i == "C3"){
                val img = findViewById<ImageView>(R.id.C3)
                img.setImageResource(R.drawable.male_booked)
                mp[C3] = false
            }

            if(i == "C4"){
                val img = findViewById<ImageView>(R.id.C4)
                img.setImageResource(R.drawable.male_booked)
                mp[C4] = false
            }

            //D
            if(i == "D1"){
                val img = findViewById<ImageView>(R.id.D1)
                img.setImageResource(R.drawable.male_booked)
                mp[D1] = false
            }

            if(i == "D2"){
                val img = findViewById<ImageView>(R.id.D2)
                img.setImageResource(R.drawable.male_booked)
                mp[D2] = false
            }

            if(i == "D3"){
                val img = findViewById<ImageView>(R.id.D3)
                img.setImageResource(R.drawable.male_booked)
                mp[D3] = false
            }

            if(i == "D4"){
                val img = findViewById<ImageView>(R.id.D4)
                img.setImageResource(R.drawable.male_booked)
                mp[D4] = false
            }

            //E
            if(i == "E1"){
                val img = findViewById<ImageView>(R.id.E1)
                img.setImageResource(R.drawable.male_booked)
                mp[E1] = false
            }

            if(i == "E2"){
                val img = findViewById<ImageView>(R.id.E2)
                img.setImageResource(R.drawable.male_booked)
                mp[E2] = false
            }

            if(i == "E3"){
                val img = findViewById<ImageView>(R.id.E3)
                img.setImageResource(R.drawable.male_booked)
                mp[E3] = false
            }

            if(i == "E4"){
                val img = findViewById<ImageView>(R.id.E4)
                img.setImageResource(R.drawable.male_booked)
                mp[E4] = false
            }

            //F
            if(i == "F1"){
                val img = findViewById<ImageView>(R.id.F1)
                img.setImageResource(R.drawable.male_booked)
                mp[F1] = false
            }

            if(i == "F2"){
                val img = findViewById<ImageView>(R.id.F2)
                img.setImageResource(R.drawable.male_booked)
                mp[F2] = false
            }

            if(i == "F3"){
                val img = findViewById<ImageView>(R.id.F3)
                img.setImageResource(R.drawable.male_booked)
                mp[F3] = false
            }

            if(i == "F4"){
                val img = findViewById<ImageView>(R.id.F4)
                img.setImageResource(R.drawable.male_booked)
                mp[F4] = false
            }

            //G
            if(i == "G1"){
                val img = findViewById<ImageView>(R.id.G1)
                img.setImageResource(R.drawable.male_booked)
                mp[G1] = false
            }

            if(i == "G2"){
                val img = findViewById<ImageView>(R.id.G2)
                img.setImageResource(R.drawable.male_booked)
                mp[G2] = false
            }

            if(i == "G3"){
                val img = findViewById<ImageView>(R.id.G3)
                img.setImageResource(R.drawable.male_booked)
                mp[G3] = false
            }

            if(i == "G4"){
                val img = findViewById<ImageView>(R.id.G4)
                img.setImageResource(R.drawable.male_booked)
                mp[G4] = false
            }

            //H
            if(i == "H1"){
                val img = findViewById<ImageView>(R.id.H1)
                img.setImageResource(R.drawable.male_booked)
                mp[H1] = false
            }

            if(i == "H2"){
                val img = findViewById<ImageView>(R.id.H2)
                img.setImageResource(R.drawable.male_booked)
                mp[H2] = false
            }

            if(i == "H3"){
                val img = findViewById<ImageView>(R.id.H3)
                img.setImageResource(R.drawable.male_booked)
                mp[H3] = false
            }

            if(i == "H4"){
                val img = findViewById<ImageView>(R.id.H4)
                img.setImageResource(R.drawable.male_booked)
                mp[H4] = false
            }

            //I
            if(i == "I1"){
                val img = findViewById<ImageView>(R.id.I1)
                img.setImageResource(R.drawable.male_booked)
                mp[I1] = false
            }

            if(i == "I2"){
                val img = findViewById<ImageView>(R.id.I2)
                img.setImageResource(R.drawable.male_booked)
                mp[I2] = false
            }

            if(i == "I3"){
                val img = findViewById<ImageView>(R.id.I3)
                img.setImageResource(R.drawable.male_booked)
                mp[I3] = false
            }

            if(i == "I4"){
                val img = findViewById<ImageView>(R.id.I4)
                img.setImageResource(R.drawable.male_booked)
                mp[I4] = false
            }

            //J
            if(i == "J1"){
                val img = findViewById<ImageView>(R.id.J1)
                img.setImageResource(R.drawable.male_booked)
                mp[J1] = false
            }

            if(i == "J2"){
                val img = findViewById<ImageView>(R.id.J2)
                img.setImageResource(R.drawable.male_booked)
                mp[J2] = false
            }

            if(i == "J3"){
                val img = findViewById<ImageView>(R.id.C3)
                img.setImageResource(R.drawable.male_booked)
                mp[J3] = false
            }

            if(i == "J4"){
                val img = findViewById<ImageView>(R.id.J4)
                img.setImageResource(R.drawable.male_booked)
                mp[J4] = false
            }

            if(i == "J5"){
                val img = findViewById<ImageView>(R.id.J5)
                img.setImageResource(R.drawable.male_booked)
                mp[J5] = false
            }
        }

        for(i in dataItemModel[0].female_occupied_seats){

            //A
            if(i == "A1"){
                val img = findViewById<ImageView>(R.id.A1)
                img.setImageResource(R.drawable.female_booked)
                mp[A1] = false
            }

            if(i == "A2"){
                val img = findViewById<ImageView>(R.id.A2)
                img.setImageResource(R.drawable.female_booked)
                mp[A2] = false
            }

            if(i == "A3"){
                val img = findViewById<ImageView>(R.id.A3)
                img.setImageResource(R.drawable.female_booked)
                mp[A3] = false
            }

            if(i == "A4"){
                val img = findViewById<ImageView>(R.id.A4)
                img.setImageResource(R.drawable.female_booked)
                mp[A4] = false
            }

            //B
            if(i == "B1"){
                val img = findViewById<ImageView>(R.id.B1)
                img.setImageResource(R.drawable.female_booked)
                mp[B1] = false
            }

            if(i == "B2"){
                val img = findViewById<ImageView>(R.id.B2)
                img.setImageResource(R.drawable.female_booked)
                mp[B2] = false
            }

            if(i == "B3"){
                val img = findViewById<ImageView>(R.id.B3)
                img.setImageResource(R.drawable.female_booked)
                mp[B3] = false
            }

            if(i == "B4"){
                val img = findViewById<ImageView>(R.id.B4)
                img.setImageResource(R.drawable.female_booked)
                mp[B4] = false
            }

            //C
            if(i == "C1"){
                val img = findViewById<ImageView>(R.id.C1)
                img.setImageResource(R.drawable.female_booked)
                mp[C1] = false
            }

            if(i == "C2"){
                val img = findViewById<ImageView>(R.id.C2)
                img.setImageResource(R.drawable.female_booked)
                mp[C2] = false
            }

            if(i == "C3"){
                val img = findViewById<ImageView>(R.id.C3)
                img.setImageResource(R.drawable.female_booked)
                mp[C3] = false
            }

            if(i == "C4"){
                val img = findViewById<ImageView>(R.id.C4)
                img.setImageResource(R.drawable.female_booked)
                mp[C4] = false
            }

            //D
            if(i == "D1"){
                val img = findViewById<ImageView>(R.id.D1)
                img.setImageResource(R.drawable.female_booked)
                mp[D1] = false
            }

            if(i == "D2"){
                val img = findViewById<ImageView>(R.id.D2)
                img.setImageResource(R.drawable.female_booked)
                mp[D2] = false
            }

            if(i == "D3"){
                val img = findViewById<ImageView>(R.id.D3)
                img.setImageResource(R.drawable.female_booked)
                mp[D3] = false
            }

            if(i == "D4"){
                val img = findViewById<ImageView>(R.id.D4)
                img.setImageResource(R.drawable.female_booked)
                mp[D4] = false
            }

            //E
            if(i == "E1"){
                val img = findViewById<ImageView>(R.id.E1)
                img.setImageResource(R.drawable.female_booked)
                mp[E1] = false
            }

            if(i == "E2"){
                val img = findViewById<ImageView>(R.id.E2)
                img.setImageResource(R.drawable.female_booked)
                mp[E2] = false
            }

            if(i == "E3"){
                val img = findViewById<ImageView>(R.id.E3)
                img.setImageResource(R.drawable.female_booked)
                mp[E3] = false
            }

            if(i == "E4"){
                val img = findViewById<ImageView>(R.id.E4)
                img.setImageResource(R.drawable.female_booked)
                mp[E4] = false
            }

            //F
            if(i == "F1"){
                val img = findViewById<ImageView>(R.id.F1)
                img.setImageResource(R.drawable.female_booked)
                mp[F1] = false
            }

            if(i == "F2"){
                val img = findViewById<ImageView>(R.id.F2)
                img.setImageResource(R.drawable.female_booked)
                mp[F2] = false
            }

            if(i == "F3"){
                val img = findViewById<ImageView>(R.id.F3)
                img.setImageResource(R.drawable.female_booked)
                mp[F3] = false
            }

            if(i == "F4"){
                val img = findViewById<ImageView>(R.id.F4)
                img.setImageResource(R.drawable.female_booked)
                mp[F4] = false
            }

            //G
            if(i == "G1"){
                val img = findViewById<ImageView>(R.id.G1)
                img.setImageResource(R.drawable.female_booked)
                mp[G1] = false
            }

            if(i == "G2"){
                val img = findViewById<ImageView>(R.id.G2)
                img.setImageResource(R.drawable.female_booked)
                mp[G2] = false
            }

            if(i == "G3"){
                val img = findViewById<ImageView>(R.id.G3)
                img.setImageResource(R.drawable.female_booked)
                mp[G3] = false
            }

            if(i == "G4"){
                val img = findViewById<ImageView>(R.id.G4)
                img.setImageResource(R.drawable.female_booked)
                mp[G4] = false
            }

            //H
            if(i == "H1"){
                val img = findViewById<ImageView>(R.id.H1)
                img.setImageResource(R.drawable.female_booked)
                mp[H1] = false
            }

            if(i == "H2"){
                val img = findViewById<ImageView>(R.id.H2)
                img.setImageResource(R.drawable.female_booked)
                mp[H2] = false
            }

            if(i == "H3"){
                val img = findViewById<ImageView>(R.id.H3)
                img.setImageResource(R.drawable.female_booked)
                mp[H3] = false
            }

            if(i == "H4"){
                val img = findViewById<ImageView>(R.id.H4)
                img.setImageResource(R.drawable.female_booked)
                mp[H4] = false
            }

            //I
            if(i == "I1"){
                val img = findViewById<ImageView>(R.id.I1)
                img.setImageResource(R.drawable.female_booked)
                mp[I1] = false
            }

            if(i == "I2"){
                val img = findViewById<ImageView>(R.id.I2)
                img.setImageResource(R.drawable.female_booked)
                mp[I2] = false
            }

            if(i == "I3"){
                val img = findViewById<ImageView>(R.id.I3)
                img.setImageResource(R.drawable.female_booked)
                mp[I3] = false
            }

            if(i == "I4"){
                val img = findViewById<ImageView>(R.id.I4)
                img.setImageResource(R.drawable.female_booked)
                mp[I4] = false
            }

            //J
            if(i == "J1"){
                val img = findViewById<ImageView>(R.id.J1)
                img.setImageResource(R.drawable.female_booked)
                mp[J1] = false
            }

            if(i == "J2"){
                val img = findViewById<ImageView>(R.id.J2)
                img.setImageResource(R.drawable.female_booked)
                mp[J2] = false
            }

            if(i == "J3"){
                val img = findViewById<ImageView>(R.id.C3)
                img.setImageResource(R.drawable.female_booked)
                mp[J3] = false
            }

            if(i == "J4"){
                val img = findViewById<ImageView>(R.id.J4)
                img.setImageResource(R.drawable.female_booked)
                mp[J4] = false
            }

            if(i == "J5"){
                val img = findViewById<ImageView>(R.id.J5)
                img.setImageResource(R.drawable.female_booked)
                mp[J5] = false
            }
        }
    }
}