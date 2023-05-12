package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.TimetableGroupFilterAdapter
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import kotlinx.coroutines.*
import java.util.*

class TimetableFilterActivity : AppCompatActivity() {
    var savedToken: String? = ""
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tametable_filter)
        val recyclerList = findViewById<RecyclerView>(R.id.recyclerViewFilterList)
        val groupBt = findViewById<Button>(R.id.buttonFilterGroup)
        val teacherBt = findViewById<Button>(R.id.buttonFilterTeacher)
        val auditoriumBt = findViewById<Button>(R.id.buttonFilterAuditorium)
        recyclerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        this.savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val groups = api.getAllGroups(savedToken.toString())
                val groupAdapter = TimetableGroupFilterAdapter(groups)
                recyclerList.adapter = groupAdapter
                groupAdapter.onClickListener = {
                    val intent = Intent(this@TimetableFilterActivity, TeacherActivity::class.java)
                    intent.putExtra("currentTeacherId", it.id)
                    startActivity(intent)
                }
            } catch (_: Exception){
                
            }
        }
    }
}