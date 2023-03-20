package com.example.mmis_lk.retrofit.interfaces

import User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("auth/login")
    suspend fun login(@Body user: String, password: String): User
}