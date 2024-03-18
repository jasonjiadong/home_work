package com.example.booksys2.mapper

import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.entity.BookEntity

class BookEntity2ItemBean:Mapper<BookEntity,BookItemBean> {
    override fun map(input: BookEntity): BookItemBean {
        return BookItemBean(
            id = input.id,
            title = input.title,
            author = input.author,
            description = input.description,
            coverImage = input.coverImage)
    }
}