package com.mustafakocer.harcamabolustur.domain.model

data class User(
    val id: String,
    val email: String,
    val username: String, // @johdoe
    val displayName: String,
    val photoUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)