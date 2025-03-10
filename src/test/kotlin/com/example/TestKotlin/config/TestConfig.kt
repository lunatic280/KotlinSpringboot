package com.example.TestKotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModules(JavaTimeModule())
        }
    }
}