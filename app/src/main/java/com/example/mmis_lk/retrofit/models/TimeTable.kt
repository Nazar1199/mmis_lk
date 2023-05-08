package com.example.mmis_lk.retrofit.models

data class TimeTable(
    val id: Int,
    val date: String,
    val group: Group,
    val auditorium: Auditorium,
    val discipline: Discipline,
    val lessonTime: LessonTime,
    val lessonType: LessonType,
    val teacher: Teacher
)