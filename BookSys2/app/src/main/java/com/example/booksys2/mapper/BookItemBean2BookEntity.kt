package com.example.booksys2.mapper

import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.entity.BookEntity

class BookItemBean2BookEntity : Mapper<BookItemBean,BookEntity> {
    override fun map(input: BookItemBean): BookEntity {
        return BookEntity(
            id = input.id,
            author = input.author,
            title = input.title,
            category = input.category,
            coverImage=input.coverImage,
            createBy = input.createBy,
            createTime =input.createTime,
            description=input.description,
            price = input.price,
            publicationDate = input.publicationDate,
            publisher = input.publisher,
            rating = input.rating,
            stockQuantity= input.stockQuantity,
            updateBy = input.updateBy,
            updateTime = input.updateTime
        )
    }
}