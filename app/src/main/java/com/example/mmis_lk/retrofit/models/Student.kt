package com.example.mmis_lk.retrofit.models

data class Student(
    val birthdate: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val patronymic: String,
    val phone: String,
    val reportCard: Int
)