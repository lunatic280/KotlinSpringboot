package com.example.TestKotlin.controller

import com.example.TestKotlin.data.BlogDto
import com.example.TestKotlin.service.BlogService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/blog")
class BlogController(
    private val blogService: BlogService
) {

    @GetMapping("/")
    fun index(): String {
        return "hello"
    }

    @GetMapping("/list")
    fun getBlogAll(): List<BlogDto> {
        return blogService.getBlogAll()
    }

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id : Long): BlogDto {
        return blogService.getBlog(id)
    }

    @PostMapping("/create-blog")
    fun createBlog(@RequestBody dto: BlogDto): BlogDto {
        return blogService.createBlog(dto)
    }

    @PostMapping("/update-blog/{id}")
    fun updatedBlog(@RequestBody dto: BlogDto, @PathVariable id: Long): BlogDto {
        return blogService.updatedBlog(id, dto)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteBlog(@PathVariable id: Long) {
        return blogService.deleteBlog(id)
    }
}