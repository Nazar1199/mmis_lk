package com.example.mmis_lk

import User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.DeltaApi
import com.example.mmis_lk.retrofit.interfaces.UserApi
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val localBaseUrl = "http://192.168.0.11:2000/api/"
        val baseUrl = "https://dummyjson.com/"

        var localeSwitch = findViewById<Switch>(R.id.switchLocal)
        var loginEmail = findViewById<EditText>(R.id.editTextTextEmail)
        var loginPassword = findViewById<EditText>(R.id.editTextTextPassword)
        var messagesView = findViewById<TextView>(R.id.textViewMessage)
        var loginBt = findViewById<Button>(R.id.buttonLogin)
        loginBt.setOnClickListener {
//            Toast.makeText(this, "Тостик для Дафки v.2", Toast.LENGTH_SHORT).show()
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            if (localeSwitch.isChecked){
              Toast.makeText(this, localBaseUrl, Toast.LENGTH_SHORT).show()
                val deltaClient = RetrofitClient.getClient(localBaseUrl)
                var deltaApi = deltaClient.create(DeltaApi::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val deltaUser = deltaApi.getUser2()
                        runOnUiThread {
                            messagesView.text = deltaUser.toString()
                        }
                    } catch (error: Exception){
                        runOnUiThread {
                            messagesView.text = "ERROR LOCAL"
                        }
                    }
                }
            } else {
                Toast.makeText(this, baseUrl, Toast.LENGTH_SHORT).show()
                val client = RetrofitClient.getClient(baseUrl)
                var userApi = client.create(UserApi::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val login = userApi.login(email, password)
                        runOnUiThread {
                            messagesView.text = login.toString()
                        }
                        runOnUiThread {
                            if (login.token != ""){
                                messagesView.text = login.token
                            } else {
                                messagesView.text = "Failed login"
                            }
                        }
                    } catch (error: Exception){
                        runOnUiThread {
                            messagesView.text = "ERROR WEB"
                        }
                    }
                }
            }
        }
    }
}