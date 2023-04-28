package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.user
import com.example.mmis_lk.retrofit.models.userLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface userApi {
    @POST("auth/login")
    fun login(@Body user: String, password: String): user
    @POST("auth/login")
    fun loginOjb(@Body userData: userLogin): user
    @POST("auth/login")
    fun loginCb(@Body userData: userLogin): Call<user>
}