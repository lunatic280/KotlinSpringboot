package com.example.TestKotlin.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "user_name", nullable = false, length = 30, unique = true)
    val userName: String? = null,

    @Column(name = "user_email", nullable = false, length = 20, unique = true)
    val userEmail: String? = null,

    @Column(name = "password" ,nullable = false)
    val password: String? = null,

    @Column(name = "created_user", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    //CasacaedType.All -> 영속성 전이, orphanRemoval -> 고아 객체 자동 삭제
    @OneToMany(mappedBy = "blog", cascade = [CascadeType.ALL], orphanRemoval = true)
    val blogList: List<Blog>? = null




)
