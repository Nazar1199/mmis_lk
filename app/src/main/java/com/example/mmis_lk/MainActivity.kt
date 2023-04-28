package com.example.mmis_lk

import UserLogin
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmis_api
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val localBaseUrl = "http://192.168.0.12:2000/api/"

        val loginEmail = findViewById<EditText>(R.id.editTextTextEmail)
        val loginPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val messagesView = findViewById<TextView>(R.id.textViewMessage)
        val loginBt = findViewById<Button>(R.id.buttonLogin)
        loginBt.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
                val client = RetrofitClient.getClient(localBaseUrl)
                val api = client.create(mmis_api::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val log = UserLogin(email, password)
                        val profile = api.login(log)
                        runOnUiThread {
                            if (profile.id != null){
                                messagesView.text = "Вход успешный!"
                            }
                        }
                    } catch (error: Exception){
                        runOnUiThread {
                            messagesView.text = "Ошибка входа"
                        }
                    }
                }
        }
    }
}