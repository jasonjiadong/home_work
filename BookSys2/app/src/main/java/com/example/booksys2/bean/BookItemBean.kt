package com.example.booksys2.bean

import java.io.Serializable

data class BookItemBean(
    val id: String ="",
    var author: String="",
    val category: String="小说",
    val coverImage: String="",
    val createBy: String="lisi",
    val createTime: String="2024-03-15 17:37:26",
    var description: String="",
    val price: Double=0.0,
    val publicationDate: String ="2024-01-01",
    val publisher: String="人民文学出版社",
    val rating: Int=5,
    val stockQuantity: Int=18,
    var title: String="",
    val updateBy: String ="2024-03-15 17:37:26",
    val updateTime: String = "2024-03-15 17:37:26",
):Serializable
