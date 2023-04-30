package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.example.mmis_lk.retrofit.models.Profile
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEmail = findViewById<EditText>(R.id.editTextTextEmail)
        val loginPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val messagesView = findViewById<TextView>(R.id.textViewMessage)
        val loginBt = findViewById<Button>(R.id.buttonLogin)
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val profileActivity = Intent(this, ProfileActivity::class.java)
        if (sharedPref.getString("token", "") != ""){
            startActivity(profileActivity)
        }
        loginBt.setOnClickListener {
            messagesView.text = ""
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            val client = RetrofitClient.getClient(resources.getString(R.string.localBaseUrl))
                val api = client.create(mmisApi::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val log = Profile(email, password)
                        val authToken = api.login(log)
                        runOnUiThread {
                            if (authToken != null){
                                var editor = sharedPref.edit()
                                editor.putString("token", authToken.token)
                                editor.commit()
                                messagesView.text = getString(R.string.login_success)
                            }
                        }
                    } catch (error: Exception){
                        runOnUiThread {
                            messagesView.text = getString(R.string.login_error)
//                            messagesView.text = error.toString()
                        }
                    }
                }
            if (sharedPref.getString("token", "") != ""){
                startActivity(profileActivity)
            }
        }
    }
}