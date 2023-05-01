package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.Profile
import com.example.mmis_lk.retrofit.models.Student
import com.example.mmis_lk.retrofit.models.Token
import retrofit2.http.*

interface mmisApi {
    @POST("profile/login")
    suspend fun login(@Body user: Profile): Token

    @GET("student/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): Student
}