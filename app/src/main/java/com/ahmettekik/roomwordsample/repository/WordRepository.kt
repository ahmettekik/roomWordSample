package com.ahmettekik.roomwordsample.repository

import android.app.Application
import com.ahmettekik.roomwordsample.database.Word
import com.ahmettekik.roomwordsample.database.WordRoomDatabase
import kotlinx.coroutines.experimental.async

class WordRepository(application: Application) {
    private val wordDao = WordRoomDatabase.getDatabase(application)?.WordDao()
    val allWords = wordDao?.getAllWords()

    fun insert(word: Word) {
        async {
            insertWord(word)
        }
    }

    private suspend fun insertWord(word: Word) {
        wordDao?.insert(word)
    }
}