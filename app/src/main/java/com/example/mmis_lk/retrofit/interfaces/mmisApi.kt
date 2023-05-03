package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.*
import retrofit2.http.*

interface mmisApi {
    @POST("profile/login")
    suspend fun login(@Body user: Profile): Token

    @GET("student/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): Student

    @GET("reference")
    suspend fun getAllReferences(): Array<Reference>

    @GET("orderingReference/me")
    suspend fun getMyReferences(@Header("Authorization") token: String): Array<OrderingReference>

    @POST("orderingReference/me")
    suspend fun orderReferenceForMe(@Body reference: Reference): OrderingReference

}