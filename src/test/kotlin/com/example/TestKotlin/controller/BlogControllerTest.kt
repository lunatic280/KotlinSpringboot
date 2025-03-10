package com.example.TestKotlin.controller

import com.example.TestKotlin.data.BlogDto
import com.example.TestKotlin.service.BlogService
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

@WebMvcTest(BlogController::class)
class BlogControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var blogService: BlogService

    lateinit var objectMapper: ObjectMapper

    val sampleBlogDto1 = BlogDto(
        title = "Test Blog1",
        content = "This is a test blog1",
        createdDate = LocalDateTime.now(),
        updatedDate = LocalDateTime.now()
    )

    val sampleBlogDto2 = BlogDto(
        title = "Test Blog2",
        content = "This is a test blog2",
        createdDate = LocalDateTime.now(),
        updatedDate = LocalDateTime.now()
    )

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        objectMapper = ObjectMapper().apply {
            registerModule(JavaTimeModule())
        }
    }

    @Test
    @DisplayName("모든 블로그 글을 호출한다.")
    fun testGetAllBlog() {
        Mockito.`when`(blogService.getBlogAll()).thenReturn(listOf(sampleBlogDto1, sampleBlogDto2))

        mockMvc.perform(MockMvcRequestBuilders.get("/blog/list"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
    }

    @Test
    @DisplayName("블로그 ID로 블로그 글을 호출한다")
    fun testGetIDBlog() {
        Mockito.`when`(blogService.getBlog(1L)).thenReturn(sampleBlogDto1)

        mockMvc.perform(MockMvcRequestBuilders.get("/blog/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Blog1"))
    }

    @Test
    @DisplayName("새로운 블로그 글을 등록한다")
    fun testCreateBlog() {
        Mockito.`when`(blogService.createBlog(sampleBlogDto1)).thenReturn(sampleBlogDto1)

        mockMvc.perform(MockMvcRequestBuilders.post("/blog/create-blog")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sampleBlogDto1)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Blog1"))

    }

    @Test
    @DisplayName("블로그 글을 업데이트 한다")
    fun testUpdateBlog() {
        val updateBlogDto = sampleBlogDto1.copy(title = "Test Update Blog")
        Mockito.`when`(blogService.updatedBlog(1L, updateBlogDto)).thenReturn(updateBlogDto)

        mockMvc.perform(MockMvcRequestBuilders.post("/blog/update-blog/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateBlogDto)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Update Blog"))
    }

    @Test
    @DisplayName("블로그 글을 삭제한다.")
    fun testDeleteBlog() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/blog/delete/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.verify(blogService, Mockito.times(1)).deleteBlog(1L)
    }

}