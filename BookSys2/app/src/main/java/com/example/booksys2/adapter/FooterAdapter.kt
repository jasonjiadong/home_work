package com.example.booksys2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.booksys2.databinding.FooterLayoutBinding
import com.example.booksys2.viewmodel.FootHolder

class FooterAdapter(var bookAdapter: BookAdapter) :LoadStateAdapter<FootHolder>(){
    override fun onBindViewHolder(holder: FootHolder, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FootHolder {
        val binding =
            FooterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FootHolder(binding) { bookAdapter.retry() }
    }
}