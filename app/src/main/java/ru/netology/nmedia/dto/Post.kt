package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    var like: Int = 0,
    var share: Int = 0,
    var view: Int = 0,
)
