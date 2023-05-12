package com.example.mmis_lk.retrofit.interfaces

import com.example.mmis_lk.retrofit.models.*
import retrofit2.http.*

interface mmisApi {
    @POST("profile/login")
    suspend fun login(@Body user: Profile): Token

    @GET("student/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): Student

    @GET("reference")
    suspend fun getAllReferences(@Header("Authorization") token: String): Array<Reference>

    @GET("orderingReference/me")
    suspend fun getMyReferences(@Header("Authorization") token: String): Array<OrderingReference>

    @POST("orderingReference/me")
    suspend fun orderReferenceForMe(@Header("Authorization") token: String, @Body reference: Reference): OrderingReference

    @GET("auditorium")
    suspend fun getAllAuditoriums(@Header("Authorization") token: String): Array<Auditorium>

    @GET("teacher")
    suspend fun getAllTeachers(@Header("Authorization") token: String): Array<Teacher>

    @GET("teacher/{id}")
    suspend fun getTeacherById(@Header("Authorization") token: String, @Path("id") id: Int): Teacher

    @GET("timetable")
    suspend fun getAllTimetables(@Header("Authorization") token: String): Array<TimeTable>

    @GET("timetable/group/{id}")
    suspend fun getTimetableByGroup(@Header("Authorization") token: String, @Path("id") groupId: Int): Array<TimeTable>

    @GET("timetable/teacher/{id}")
    suspend fun getTimetableByTeacher(@Header("Authorization") token: String, @Path("id") teacherId: Int): Array<TimeTable>

    @GET("timetable/auditorium/{id}")
    suspend fun getTimetableByAuditorium(@Header("Authorization") token: String, @Path("id") auditoriumId: Int): Array<TimeTable>

    @GET("group")
    suspend fun getAllGroups(@Header("Authorization") token: String): Array<Group>
}