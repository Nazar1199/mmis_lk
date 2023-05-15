package com.example.mmis_lk.retrofit.models

data class Certification(
    val dateTime: String,
    val discipline: Discipline,
    val id: Int,
    val mark: Int,
    val numberOfHours: Int,
    val periodSem: Int,
    val student: Student,
    val teacher: Teacher
)