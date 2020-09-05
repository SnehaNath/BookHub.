package com.intershala.bookhub.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BookDao {

    @Insert
    fun insertBook(bookEntity: BookEntity/*Book we need to add to the table*/)

    @Delete
    fun deleteBook(bookEntity: BookEntity)


    @Query("select * from books")
    fun getAllBooks(): List<BookEntity>


    @Query("select * from books where book_id = :bookId")
    fun getBookById(bookId: String): BookEntity
}
