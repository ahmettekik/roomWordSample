package com.ahmettekik.roomwordsample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.ahmettekik.roomwordsample.database.Word
import com.ahmettekik.roomwordsample.database.WordRoomDatabase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

class WordRepository(application: Application) {
    private val wordDao = WordRoomDatabase.getDatabase(application)?.wordDao()

    internal fun getAllWords(): LiveData<List<Word>>? {
        return runBlocking(CommonPool) {
            async {
                queryAllWords()
            }.await()
        }
    }

    private suspend fun queryAllWords(): LiveData<List<Word>>? {
        return wordDao?.getAllWords()
    }

    fun insert(word: Word) = launch { insertWord(word) }


    private suspend fun insertWord(word: Word) {
        wordDao?.insert(word)
    }
}