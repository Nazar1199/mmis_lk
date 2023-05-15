package com.example.mmis_lk

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mmis_lk.adapters.SemCertificationAdapter
import com.example.mmis_lk.retrofit.RetrofitClient
import com.example.mmis_lk.retrofit.interfaces.mmisApi
import com.example.mmis_lk.retrofit.models.Certification
import kotlinx.coroutines.*
import java.util.*

class CertificationActivity : AppCompatActivity() {
    var savedToken: String? = ""
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification)

        val recyclerList = findViewById<RecyclerView>(R.id.recyclerViewCertificationSemester)
        recyclerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        this.savedToken = sharedPref.getString("token", "")
        val client = RetrofitClient.getClient(resources.getString(R.string.local_base_url))
        val api = client.create(mmisApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var certifications = api.getMyCertifications(savedToken.toString())

                certifications.sortBy { it.periodSem }
                var sortingCertifications: MutableList<Array<Certification>> = arrayListOf()
                var sortingCertificationsForSem: MutableList<Certification> = arrayListOf()
                if (certifications.isNotEmpty()){
                    var startDate = certifications[0].periodSem
                    certifications.forEach {
                        if (it.periodSem == startDate){
                            sortingCertificationsForSem.add(it)
                        } else {
                            sortingCertifications.add(sortingCertificationsForSem.toTypedArray())
                            sortingCertificationsForSem = arrayListOf()
                            sortingCertificationsForSem.add(it)
                            startDate = it.periodSem
                        }
                    }
                    sortingCertifications.add(sortingCertificationsForSem.toTypedArray())
                    val sortedCertifications: Array<Array<Certification>> = sortingCertifications.toTypedArray()
                    runOnUiThread { recyclerList.adapter = SemCertificationAdapter(sortedCertifications) }
                }
            } catch (error: Exception){

            }
        }
    }
}