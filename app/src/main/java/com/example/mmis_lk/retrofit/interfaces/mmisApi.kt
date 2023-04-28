package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.userLogin
import com.example.mmis_lk.retrofit.models.profile
import retrofit2.http.Body
import retrofit2.http.POST

interface mmisApi {
//    @POST("profile/login")
//    fun login(@Body email: String, password: String): profile
    @POST("profile/login")
    suspend fun login(@Body user: userLogin): profile
}