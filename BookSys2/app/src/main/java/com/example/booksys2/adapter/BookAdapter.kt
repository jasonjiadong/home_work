package com.example.booksys2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.databinding.ItemBookLayoutBinding
import com.example.booksys2.viewholder.BookViewHolder
import com.example.booksys2.viewmodel.BookListFragmentViewModel


class BookAdapter(val context: Context,val viewModel: BookListFragmentViewModel) : PagingDataAdapter<BookItemBean,BookViewHolder>(object :
    DiffUtil.ItemCallback<BookItemBean>(){

    override fun areItemsTheSame(oldItem: BookItemBean, newItem: BookItemBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookItemBean, newItem: BookItemBean): Boolean {
        return oldItem==newItem
    }
}) {
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        var binding =   holder.mBindding as ItemBookLayoutBinding
        getItem(position).let {
            item->
              binding.itemBook = item
            binding.apply {
                btnDele.setOnClickListener {
                    viewModel.deleBookById(item!!.id)
                }
                btnEdit.setOnClickListener{
                    viewModel.editBook(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var binding =  ItemBookLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return BookViewHolder(binding)
    }


}