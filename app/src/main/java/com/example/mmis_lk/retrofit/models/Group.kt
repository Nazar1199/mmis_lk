package com.example.mmis_lk.retrofit.models

data class Group(
    val course: Course,
    val faculty: Faculty,
    val id: Int,
    val name: String,
    val year: Int
)