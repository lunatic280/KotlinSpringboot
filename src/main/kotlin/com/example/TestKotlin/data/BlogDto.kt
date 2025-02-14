package com.example.TestKotlin.data

import java.time.LocalDateTime

data class BlogDto(
    val title: String,
    val content: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
)
