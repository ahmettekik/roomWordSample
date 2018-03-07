package com.ahmettekik.roomwordsample.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.async

@Database(entities = arrayOf(Word::class), version = 1)
abstract class WordRoomDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object : RoomDatabase.Callback(){
        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (instance == null) {
                synchronized(WordRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java, "word_database")
                            .addCallback(this)
                            .build()
                }
            }
            return instance
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            async {
                populateDatabase(instance)
            }
        }

        private suspend fun populateDatabase(instance: WordRoomDatabase?) {
            val dao = instance?.wordDao()
            dao?.deleteAllWords()
            dao?.insert(Word("Hello"))
            dao?.insert(Word("World"))
        }
    }
}
