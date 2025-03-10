package com.example.TestKotlin.service

import com.example.TestKotlin.data.BlogDto
import com.example.TestKotlin.entity.Blog
import com.example.TestKotlin.repository.BlogRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime
import java.util.Optional

class BlogServiceTest {
    lateinit var blogService: BlogService

    @Mock
    lateinit var blogRepository: BlogRepository

    val sampleBlog = Blog(
        id = 1L,
        title = "Test Blog",
        content = "This is a test blog",
        createdDate = LocalDateTime.now(),
        updatedDate = LocalDateTime.now()
    )

    val sampleBlogDto = BlogDto(
        title = "Test Blog",
        content = "This is a test blog",
        createdDate = sampleBlog.createdDate,
        updatedDate = sampleBlog.updatedDate
    )

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        blogService = BlogService(blogRepository)
    }

    @Test
    @DisplayName("새로운 블로그를 생성한다.")
    fun testCreateBlog() {
        Mockito.`when`(blogRepository.save(Mockito.any())).thenReturn(sampleBlog)

        val result = blogService.createBlog(blogDto = sampleBlogDto)

        assert(result.title == "Test Blog")
        assert(result.content == "This is a test blog")
    }

    @Test
    @DisplayName("블로그를 업데이트 한다")
    fun testUpdateBlog() {
        val updateBlogDto = sampleBlogDto.copy(title = "Updated Blog")
        val updateBlog = sampleBlog.copy(title = "Updated Blog")

        Mockito.`when`(blogRepository.findById(1L)).thenReturn(Optional.of(sampleBlog))
        Mockito.`when`(blogRepository.save(Mockito.any())).thenReturn(updateBlog)

        val result = blogService.updatedBlog(1L, updateBlogDto)

        assert(result.title == "Updated Blog")
        assert(result.content == "This is a test blog")
    }

    @Test
    @DisplayName("블로그의 ID로 블로그를 검색한다")
    fun testGetIdBlog() {
        Mockito.`when`(blogRepository.findById(1L)).thenReturn(Optional.of(sampleBlog))

        val result = blogService.getBlog(1L)

        assertThat(result.title == sampleBlogDto.title)
        assertThat(result.title == sampleBlogDto.content)
    }

    @Test
    @DisplayName("블로그를 삭제한다")
    fun testDeleteBlog() {
        Mockito.doNothing().`when`(blogRepository).deleteById(1L)

        blogService.deleteBlog(1L)

        Mockito.verify(blogRepository, Mockito.times(1)).deleteById(1L)
    }
}