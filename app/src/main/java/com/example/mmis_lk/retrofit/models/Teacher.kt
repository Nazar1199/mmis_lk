package com.example.mmis_lk.retrofit.models

data class Teacher(
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val position: Position,
    val patronymic: String,
    val phone: String,
    val photo: String,
    val department: Department
)