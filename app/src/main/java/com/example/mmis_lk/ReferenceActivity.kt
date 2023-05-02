package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.OrderingReferenceAdapter
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import kotlinx.coroutines.*
import java.util.*

class ReferenceActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reference)

        val orderReferenceBt = findViewById<Button>(R.id.buttonOrderReference)
        val recyclerList = findViewById<RecyclerView>(R.id.recyclerViewReferenceList)
        recyclerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.localBaseUrl))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val myRefs = api.getMyReferences(savedToken.toString())
                recyclerList.adapter = OrderingReferenceAdapter(myRefs)
            } catch (error: Exception){
                
            }
        }
    }
}