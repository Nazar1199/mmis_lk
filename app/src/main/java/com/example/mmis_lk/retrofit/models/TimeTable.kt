package com.example.mmis_lk.retrofit.models

import java.util.Date

data class TimeTable(
    val id: Int,
    val date: Date,
    val group: Group,
    val auditorium: Auditorium,
    val discipline: Discipline,
    val lessonTime: LessonTime,
    val lessonType: LessonType,
    val teacher: Teacher
)