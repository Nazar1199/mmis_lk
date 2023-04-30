package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.Profile
import com.example.mmis_lk.retrofit.models.Student
import com.example.mmis_lk.retrofit.models.Token
import retrofit2.http.*

interface mmisApi {
//    @POST("profile/login")
//    fun login(@Body email: String, password: String): profile
    @POST("profile/login")
    suspend fun login(@Body user: Profile): Token

    @GET("student/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): Student
}