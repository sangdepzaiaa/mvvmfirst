package com.example.myapplication.demoviewModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFrist3Binding


class FristFragment3 : BaseFragment1WithViewBinding<FragmentFrist3Binding>(
    inflateViewBinding = FragmentFrist3Binding::inflate
) {
    private val viewmodel by activityViewModels<ShareViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.btn.setOnClickListener {
           viewmodel.counterUp()
       }
    }





}