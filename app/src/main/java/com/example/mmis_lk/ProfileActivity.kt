package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val name = findViewById<TextView>(R.id.textViewName)
        val logOutBt = findViewById<Button>(R.id.buttonLogout)
        val avatar = findViewById<ImageView>(R.id.imageViewAvatar)
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedToken = sharedPref.getString("token", "")
        email.text = savedToken
        val client = RetrofitClient.getClient(resources.getString(R.string.localBaseUrl))
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
                        Picasso.get().load(currentStudent.photo).into(avatar)
                    }
                }
            } catch (error: Exception){
                runOnUiThread {
                    val err = "Ошибка входа"
//                    phone.text = error.toString()
                }
            }
        }
        logOutBt.setOnClickListener {
            var editor = sharedPref.edit()
            editor.putString("token", "")
            editor.commit()
            if (sharedPref.getString("token", "") == ""){
                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
    }
}