package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.deltaUser
import retrofit2.http.GET

interface DeltaApi {
    @GET("users/2")
    suspend fun getUser2(): deltaUser
}