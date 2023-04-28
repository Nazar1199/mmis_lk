package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.userLogin
import com.example.mmis_lk.retrofit.models.profile
import com.example.mmis_lk.retrofit.models.student
import com.example.mmis_lk.retrofit.models.token
import retrofit2.http.*

interface mmisApi {
//    @POST("profile/login")
//    fun login(@Body email: String, password: String): profile
    @POST("profile/login")
    suspend fun login(@Body user: profile): token

    @GET("student/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): student
}