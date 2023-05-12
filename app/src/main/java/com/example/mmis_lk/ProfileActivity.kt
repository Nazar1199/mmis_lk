package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val email = findViewById<TextView>(R.id.textViewEmailValue)
        val phone = findViewById<TextView>(R.id.textViewPhoneValue)
        val group = findViewById<TextView>(R.id.textViewGroupValue)
        val reportCard = findViewById<TextView>(R.id.textViewReportCardValue)
        val birthDate = findViewById<TextView>(R.id.textViewBirthDateValue)
        val name = findViewById<TextView>(R.id.textViewName)
        val logOutBt = findViewById<Button>(R.id.buttonLogout)
        val myRefsBt = findViewById<Button>(R.id.buttonMyReferences)
        val toTimetable = findViewById<Button>(R.id.buttonToTimetable)
        val toTeachersBt = findViewById<Button>(R.id.buttonToTeachersList)
        val avatar = findViewById<ImageView>(R.id.imageViewAvatar)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currentStudent = api.getMyProfile(savedToken.toString())
                runOnUiThread {
                    if (currentStudent.id !== null){
                        name.text = currentStudent.firstName + " " + currentStudent.lastName
                        email.text = currentStudent.email
                        phone.text = currentStudent.phone
                        group.text = currentStudent.group.name
                        birthDate.text = currentStudent.birthdate
                        reportCard.text = currentStudent.reportCard
                        Picasso.get()
                            .load(currentStudent.photo)
                            .into(avatar)
                    }
                }
            } catch (error: Exception){
                runOnUiThread {
                    val err = getString(R.string.login_error)
//                    phone.text = error.toString()
                }
            }
        }
        logOutBt.setOnClickListener {
            var editor = sharedPref.edit()
            editor.putString("token", "")
            editor.commit()
            if (sharedPref.getString("token", "") == ""){
                val loginActivity = Intent(this, LoginActivity::class.java)
                startActivity(loginActivity)
            }
        }
        myRefsBt.setOnClickListener {
            val referencesActivity = Intent(this, ReferenceActivity::class.java)
            startActivity(referencesActivity)
        }
        toTeachersBt.setOnClickListener {
            val teacherListActivity = Intent(this, TeacherListActivity::class.java)
            startActivity(teacherListActivity)
        }
        toTimetable.setOnClickListener {
            val timetableActivity = Intent(this, TimetableFilterActivity::class.java)
            startActivity(timetableActivity)
        }
    }
}