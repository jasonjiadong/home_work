package com.example.booksys2.viewmodel

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.booksys2.databinding.FooterLayoutBinding

class FootHolder(
    private val mBinding: FooterLayoutBinding,
    val retryCallback: () -> Unit):RecyclerView.ViewHolder(mBinding.root) {
    fun bindData(data: LoadState){
        mBinding.apply {
            // 正在加载，显示进度条
            progress.isVisible = data is LoadState.Loading
            // 加载失败，显示并点击重试按钮
            retryButton.isVisible = data is LoadState.Error
            retryButton.setOnClickListener { retryCallback() }
            // 加载失败显示错误原因
            errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (data as? LoadState.Error)?.error?.message
        }
    }
    inline var View.isVisible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }
}