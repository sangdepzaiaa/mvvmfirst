package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentThirdBinding


class ThirdFragment : BaseFragment1WithViewBinding<FragmentThirdBinding>(
    inflateViewBinding = FragmentThirdBinding::inflate,
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnback2.setOnClickListener {
            parentFragmentManager.popBackStack() // back lại frag trước đó
        }
        binding.btnback1.setOnClickListener {
            // flag = 0 -> SecondFragment is displayed
            // flag = POP_BACK_STACK_INCLUSIVE -> FirstFragment is displayed
            parentFragmentManager.popBackStack("first_to_second",FragmentManager.POP_BACK_STACK_INCLUSIVE)
            // transaction:
            //             flag POP_BACK_STACK_INCLUSIVE -> display 1
            //         FIRST_TO_SECOND
            //             flag 0 ->   ..... display 2
            //         SECOND_TO_THIRD
        }

    }

}