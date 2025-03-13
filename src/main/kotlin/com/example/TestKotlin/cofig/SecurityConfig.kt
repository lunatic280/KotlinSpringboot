package com.example.TestKotlin.cofig

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
class SecurityConfig {

    private val userDetailsService: UserDetailsService
    private val objectMapper: ObjectMapper
}