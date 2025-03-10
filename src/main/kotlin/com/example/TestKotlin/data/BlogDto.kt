package com.example.TestKotlin.data

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class BlogDto(
    val title: String,
    val content: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime,


)
