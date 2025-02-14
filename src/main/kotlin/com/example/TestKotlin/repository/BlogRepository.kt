package com.example.TestKotlin.repository

import com.example.TestKotlin.entity.Blog
import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Blog, Long> {
}