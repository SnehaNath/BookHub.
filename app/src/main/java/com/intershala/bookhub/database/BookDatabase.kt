package com.intershala.bookhub.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class],version = 1)       //mentioning the entities that we need inside the class     //version of the DB:Int->imp. so app doesn't get crashed when user updates the app
abstract class BookDatabase: RoomDatabase() {

    //telling -> all the func. that we'll perform on the data will be performed by the Dao interface
    abstract fun bookDao(): BookDao         //allow us to use all the functionalities of a Dao      //abstract class -> no implementation needed

}