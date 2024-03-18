package com.example.booksys2.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.booksys2.bean.BookItemBean
import com.example.booksys2.databinding.FragmentBookUpdateBinding
import com.example.booksys2.viewmodel.BookUpdateFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [BookUpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BookUpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isAdd :Boolean = false
    private var bookItemBean:BookItemBean ?= null
    private lateinit var mBinding:FragmentBookUpdateBinding
    private val viewModle: BookUpdateFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       var boundl =  requireArguments()
        Log.d("qin", "BookUpdateFragment_onCreate: $boundl")
        boundl?.let {
            isAdd = boundl.getBoolean("isAdd")
            if(boundl.containsKey("book")){
                bookItemBean = boundl.getSerializable("book") as BookItemBean
            }
        }
        Log.d("qin", "BookUpdateFragment_onCreate: $isAdd,  ${bookItemBean?.author}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentBookUpdateBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            if(!isAdd){
                mBinding.book= bookItemBean
            }else{
                mBinding.book= BookItemBean()
            }
        mBinding.btnUpdate.setOnClickListener {
            mBinding.book!!.title = mBinding.etNameValue.text.toString()
            mBinding.book!!.author = mBinding.etAuthorValue.text.toString()
            mBinding.book!!.description = mBinding.etDescValue.text.toString()
            Log.d("qin", "onViewCreated  btnUpdate: ${mBinding.book}")
            viewModle.updateOrDeleteBook(isAdd,mBinding.book!!)
        }

        viewModle.insertBook.observe(viewLifecycleOwner){
            viewModle.setData(it.success)
            Log.d("qin", "onViewCreated: insertBook")
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        }
        viewModle.updateBook.observe(viewLifecycleOwner){
            viewModle.setData(it.success)
            Log.d("qin", "onViewCreated: updateBook")
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookUpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookUpdateFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }
}