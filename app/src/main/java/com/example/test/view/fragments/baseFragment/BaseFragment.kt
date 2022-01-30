package com.example.test.view.fragments.baseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.google.android.material.appbar.MaterialToolbar

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewDataBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private lateinit var _binding: VB
    protected val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        if(isShowToolBar()) {
            actionBar?.title = ""
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.show()

            val toolbar: MaterialToolbar? = activity?.findViewById(R.id.toolbar)

            toolbar?.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

        }else{
            actionBar?.hide()
        }

        _binding = inflate.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    abstract fun isShowToolBar() : Boolean


}