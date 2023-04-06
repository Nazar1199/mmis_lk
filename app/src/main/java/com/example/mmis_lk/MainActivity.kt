package com.example.mmis_lk

import UserLogin
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmis_api
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val localBaseUrl = "http://192.168.0.13:2000/api/"

        var loginEmail = findViewById<EditText>(R.id.editTextTextEmail)
        var loginPassword = findViewById<EditText>(R.id.editTextTextPassword)
        var messagesView = findViewById<TextView>(R.id.textViewMessage)
        var loginBt = findViewById<Button>(R.id.buttonLogin)
        loginBt.setOnClickListener {
//            Toast.makeText(this, "Тостик для Дафки v.2", Toast.LENGTH_SHORT).show()
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
                val client = RetrofitClient.getClient(localBaseUrl)
                var api = client.create(mmis_api::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        var log = UserLogin(email, password)
                        val profile = api.login(log)
                        runOnUiThread {
                            if (profile.id != null){
                                messagesView.text = "Вход успешный!"
                            }
//                            messagesView.text = profile.toString()
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