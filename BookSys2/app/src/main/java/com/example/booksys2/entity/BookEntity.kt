package com.example.booksys2.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//"bookId": 6,
//"title": "战争与和平",
//"author": "列夫·托尔斯泰",
//"publisher": "人民文学出版社",
//"publicationDate": "1989-01-01",
//"category": "小说",
//"price": 59.9,
//"stockQuantity": 18,
//"coverImage": "cover_image8.jpg",
//"description": "一部史诗般的巨著，描绘了俄国社会的广阔画面。",
//"rating": 5,
//"createBy": null,
//"createTime": null,
//"updateBy": null,
//"updateTime": null
@Entity
data class BookEntity(
    @PrimaryKey
    val id: String,
    var author: String = "张三",
    val category: String = "小说",
    val coverImage: String,
    val createBy: String = "lisi",
    val createTime: String = "",
    val description: String,
    val price: Double = 66.6,
    val publicationDate: String = "1989-01-01",
    val publisher: String = "人民文学出版社",
    val rating: Int = 5,
    val stockQuantity: Int = 18,
    val title: String,
    val updateBy: String = "",
    val updateTime: String = "",
    val page: Int = 0
)

