package com.ahmettekik.roomwordsample.database

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun WordDao(): WordDao

    companion object {
        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (instance == null) {
                synchronized(WordRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java, "word_database")
                            .build()
                }
            }
            return instance
        }
    }
}
