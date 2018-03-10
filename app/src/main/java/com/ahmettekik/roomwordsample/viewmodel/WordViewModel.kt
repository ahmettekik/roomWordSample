package com.ahmettekik.roomwordsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.ahmettekik.roomwordsample.database.Word
import com.ahmettekik.roomwordsample.repository.WordRepository

class WordViewModel(application: Application): AndroidViewModel(application) {
    private val repository = WordRepository(application)
    val allWords = repository.getAllWords()

    fun insert(word: Word) {
        repository.insert(word)
    }
}