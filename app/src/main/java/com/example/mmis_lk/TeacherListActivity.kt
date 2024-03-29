package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.TeacherAdapter
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import kotlinx.coroutines.*
import java.util.*

class TeacherListActivity : AppCompatActivity() {
    var savedToken: String? = ""
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_list)
        val recyclerList = findViewById<RecyclerView>(R.id.recyclerViewTeacherList)
        recyclerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        this.savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val teachers = api.getAllTeachers(savedToken.toString())
                val myAdapter = TeacherAdapter(teachers)
                recyclerList.adapter = myAdapter
                myAdapter.onClickListener = {
                    val intent = Intent(this@TeacherListActivity, TeacherActivity::class.java)
                    intent.putExtra("currentTeacherId", it.id)
                    startActivity(intent)
                }
            } catch (_: Exception){
                
            }
        }
    }
}