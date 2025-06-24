package com.example.myapplication.Fragments2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment2<VB : ViewBinding>(
    val infla: (inlafater: LayoutInflater,parent: ViewGroup?, attach: Boolean) -> VB
) : Fragment() {

    private var _binding : VB ?=null
    protected val binding : VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = infla(inflater,container,false)
        return binding.root // view cha chứa toàn bộ view con
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

