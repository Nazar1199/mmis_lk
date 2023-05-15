package com.example.mmis_lk.retrofit.models

import java.util.Date

data class Certification(
    val dateTime: Date,
    val discipline: Discipline,
    val id: Int,
    val mark: Int,
    val numberOfHours: Int,
    val periodSem: Int,
    val student: Student,
    val teacher: Teacher,
    val typeControl: TypeControl
)