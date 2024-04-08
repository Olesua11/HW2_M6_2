package com.example.hw2_m6_2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.hw2_m6_2.di.Resource

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflate<VB>
) : Fragment() {

    protected abstract val viewModel: VM
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observe()
    }

    open fun setupViews() {}
    open fun observe() {}

    protected fun <T> LiveData<Resource<T>>.stataHandler(
        success: (data: T) -> Unit,
        state: (res: Resource<T>) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { res ->
            state(res)
            when (res) {
                is Resource.Error -> {
                    Log.e("Resource.Error", "${res.massage}")
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    res.data?.let { success(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
