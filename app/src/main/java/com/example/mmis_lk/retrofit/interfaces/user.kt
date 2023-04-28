package com.example.mmis_lk.retrofit.interfaces

import User
import com.example.mmis_lk.retrofit.models.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("auth/login")
    fun login(@Body user: String, password: String): User
    @POST("auth/login")
    fun loginOjb(@Body userData: UserLogin): User
    @POST("auth/login")
    fun loginCb(@Body userData: UserLogin): Call<User>
}