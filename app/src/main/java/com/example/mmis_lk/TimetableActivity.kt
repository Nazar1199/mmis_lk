package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.DayTimetableAdapter
import com.example.mmis_lk.classes.ReferenceListDialog
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.example.mmis_lk.retrofit.models.TimeTable
import kotlinx.coroutines.*
import java.util.*

class TimetableActivity : AppCompatActivity() {
    var savedToken: String? = ""
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val recyclerList = findViewById<RecyclerView>(R.id.recyclerViewTimetableDayList)
        recyclerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        this.savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var timetables = api.getAllTimetables(savedToken.toString())
                timetables.sortBy { it.date }
                var sortingTimetables: MutableList<Array<TimeTable>> = arrayListOf()
                var sortingTimetablesForDay: MutableList<TimeTable> = arrayListOf()
                var startDate = timetables[0].date
                timetables.forEach {
                    if (it.date == startDate){
                        sortingTimetablesForDay.add(it)
                    } else {
                        sortingTimetables.add(sortingTimetablesForDay.toTypedArray())
                        sortingTimetablesForDay = arrayListOf()
                        sortingTimetablesForDay.add(it)
                        startDate = it.date
                    }
                }
                sortingTimetables.add(sortingTimetablesForDay.toTypedArray())
                val arr: Array<Array<TimeTable>> = sortingTimetables.toTypedArray()
                recyclerList.adapter = DayTimetableAdapter(arr)
            } catch (error: Exception){
                
            }
        }
    }
}