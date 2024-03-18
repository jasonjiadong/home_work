package com.example.booksys2.mapper

interface Mapper<I,O> {
    fun map(input:I):O
}