package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class TeacherActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        val email = findViewById<TextView>(R.id.textViewEmailValue)
        val position = findViewById<TextView>(R.id.textViewPositionValue)
        val name = findViewById<TextView>(R.id.textViewName)
        val avatar = findViewById<ImageView>(R.id.imageViewTeacherAvatar)

        val currentTeacherId = intent.getIntExtra("currentTeacherId", 0).toInt()
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.localBaseUrl))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currentTeacher = api.getTeacherById(savedToken.toString(), currentTeacherId)
                runOnUiThread {
                    if (currentTeacher.id !== null){
                        name.text = currentTeacher.lastName + " " + currentTeacher.firstName + " " + currentTeacher.patronymic
                        email.text = currentTeacher.email
                        position.text = currentTeacher.position.name
                        Picasso.get()
                            .load(currentTeacher.photo)
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
    }
}