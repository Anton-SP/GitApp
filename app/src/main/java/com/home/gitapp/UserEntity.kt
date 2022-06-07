package com.home.gitapp

data class UserEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val type: String,
    val siteAdmin: Boolean
)