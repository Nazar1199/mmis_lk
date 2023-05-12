package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.TimetableAuditoriumFilterAdapter
import com.example.mmis_lk.adapters.TimetableGroupFilterAdapter
import com.example.mmis_lk.adapters.TimetableTeacherFilterAdapter
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

        groupBt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val groups = api.getAllGroups(savedToken.toString())
                    val groupAdapter = TimetableGroupFilterAdapter(groups)
                    runOnUiThread {
                        recyclerList.adapter = groupAdapter
                        groupAdapter.onClickListener = {
                            val timetableActivity = Intent(this@TimetableFilterActivity, TimetableActivity::class.java)
                            timetableActivity.putExtra("filterByGroup", true)
                            timetableActivity.putExtra("filterByTeacher", false)
                            timetableActivity.putExtra("filterByAuditorium", false)
                            timetableActivity.putExtra("currentGroupIdFilter", it.id)
                            startActivity(timetableActivity)
                        }
                    }
                } catch (e: Exception){ }
            }
            teacherBt.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val teachers = api.getAllTeachers(savedToken.toString())
                        runOnUiThread {
                            val teacherAdapter = TimetableTeacherFilterAdapter(teachers)
                            recyclerList.adapter = teacherAdapter
                            teacherAdapter.onClickListener = {
                                val timetableActivity = Intent(this@TimetableFilterActivity, TimetableActivity::class.java)
                                timetableActivity.putExtra("filterByGroup", false)
                                timetableActivity.putExtra("filterByTeacher", true)
                                timetableActivity.putExtra("filterByAuditorium", false)
                                timetableActivity.putExtra("currentTeacherIdFilter", it.id)
                                startActivity(timetableActivity)
                            }
                        }
                    } catch (e: Exception){ }
                }
            }
            auditoriumBt.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val auditoriums = api.getAllAuditoriums(savedToken.toString())
                        runOnUiThread {
                            val auditoriumAdapter = TimetableAuditoriumFilterAdapter(auditoriums)
                            recyclerList.adapter = auditoriumAdapter
                            auditoriumAdapter.onClickListener = {
                                val timetableActivity = Intent(this@TimetableFilterActivity, TimetableActivity::class.java)
                                timetableActivity.putExtra("filterByGroup", false)
                                timetableActivity.putExtra("filterByTeacher", false)
                                timetableActivity.putExtra("filterByAuditorium", true)
                                timetableActivity.putExtra("currentAuditoriumIdFilter", it.id)
                                startActivity(timetableActivity)
                            }
                        }
                    } catch (e: Exception){ }
                }
            }
        }
        groupBt.callOnClick()
    }
}