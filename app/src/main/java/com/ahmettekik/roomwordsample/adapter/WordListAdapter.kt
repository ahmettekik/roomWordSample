package com.ahmettekik.roomwordsample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmettekik.roomwordsample.R
import com.ahmettekik.roomwordsample.database.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view)

class WordListAdapter : RecyclerView.Adapter<WordViewHolder>() {
    internal var words: MutableList<Word> = mutableListOf()
    override fun getItemCount() = words.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val singleWordView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,
                parent, false)
        return WordViewHolder(singleWordView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (words.isNotEmpty()) {
            holder.view.textView.text = words[position].word
        } else {
            holder.view.textView.text = holder.view.context.getString(R.string.no_word)
        }
    }
}
