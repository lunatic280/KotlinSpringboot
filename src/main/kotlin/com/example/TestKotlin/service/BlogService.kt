package com.example.TestKotlin.service

import com.example.TestKotlin.data.BlogDto
import com.example.TestKotlin.entity.Blog
import com.example.TestKotlin.repository.BlogRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class BlogService(
    private val blogRepository : BlogRepository
) {
    fun createBlog(blogDto: BlogDto): BlogDto {
        val blog = Blog(
            title = blogDto.title,
            content = blogDto.content,
            createdDate = blogDto.createdDate,
            updatedDate = blogDto.updatedDate
        )
        val savedBlog = blogRepository.save(blog)
        return savedBlog.toDto()
    }

    fun updatedBlog(id: Long, newBlogDto: BlogDto): BlogDto {
        val getBlog = blogRepository.findById(id).orElseThrow{
            IllegalArgumentException("Blog not found")
        }

        val updatedBlog = getBlog.copy(
            title = newBlogDto.title,
            content = newBlogDto.content,
            createdDate = getBlog.createdDate,
            updatedDate = newBlogDto.updatedDate
        )
        return blogRepository.save(updatedBlog).toDto()
    }

    fun getBlogAll(): List<BlogDto> {
        val blogList = blogRepository.findAll()
        return blogList.map { it.toDto() }
    }

    fun getBlog(id: Long): BlogDto {
        val blog = blogRepository.findById(id).orElseThrow{
            IllegalArgumentException("Blog not found")
        }
        return blog.toDto()
    }

    fun deleteBlog(id: Long) {
        blogRepository.deleteById(id)
    }

}