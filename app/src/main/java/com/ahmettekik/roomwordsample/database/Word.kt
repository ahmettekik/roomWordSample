package com.ahmettekik.roomwordsample.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Entity(tableName = "word_table")
data class Word(@PrimaryKey var word: String)

@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAllWords()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}

