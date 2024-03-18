package com.example.booksys2.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.booksys2.R
import com.example.booksys2.adapter.BookAdapter
import com.example.booksys2.adapter.FooterAdapter
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.databinding.FragmentBookListBinding
import com.example.booksys2.viewmodel.BookListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [BookListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BookListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mBinding: FragmentBookListBinding
    private val viewModle: BookListFragmentViewModel by viewModels()
    private val adapter: BookAdapter by lazy {
        BookAdapter(context = activity as Context,viewModle)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("ARG_PARAM1")
            param2 = it.getString("ARG_PARAM2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentBookListBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            mBinding.recyBook.adapter = adapter.run { withLoadStateFooter(FooterAdapter(this)) }

            viewModle.data.observe(viewLifecycleOwner) {
                Log.d("qin", "onViewCreated: $it")
                adapter.submitData(lifecycle, it)
                mBinding.refreshLayout.isEnabled= false
            }
            viewModle.deleResult.observe(viewLifecycleOwner){
                if(it){
                    Log.d("qin", "onViewCreated:删除成功 ")
                    adapter.refresh()
                }
            }
            viewModle.editBookData.observe(viewLifecycleOwner){
                Log.d("qin", "onViewCreated: 收到编辑的点击事件$it")
                if(it!=null){
                    findNavController().navigate(R.id.action_bookListFragment_to_bookUpdateFragment,it)
                    viewModle.editBookData.value = null
                }
            }
            viewModle.refreshData.observe(viewLifecycleOwner){
                Log.d("qin", "onViewCreated: refreshData${it}")
                if(it){
                    adapter.refresh()
                }
            }
            //监听adapter状态
            lifecycleScope.launch { adapter.loadStateFlow.collect {
                //根据刷新状态来通知swiprefreshLayout是否刷新完毕
                mBinding.refreshLayout.isRefreshing = it.refresh is LoadState.Loading
            }
            }
            mBinding.floating.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean("isAdd", true) // 设置一个名为"itemId"的整数参数
                findNavController().navigate(R.id.action_bookListFragment_to_bookUpdateFragment,bundle)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }
}