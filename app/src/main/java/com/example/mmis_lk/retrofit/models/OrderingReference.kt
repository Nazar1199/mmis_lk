package com.example.mmis_lk.retrofit.models

data class OrderingReference(
    val date: String,
    val id: Int,
    val reference: Reference,
    val status: Status
)