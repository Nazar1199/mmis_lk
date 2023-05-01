package com.example.mmis_lk.retrofit.models

import java.util.Date

data class OrderingReference(
    val id: Int,
    val date: Date,
    val reference: Reference,
    val status: Status
)