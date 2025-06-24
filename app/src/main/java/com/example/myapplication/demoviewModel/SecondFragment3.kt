package com.example.myapplication.demoviewModel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Fragments.BaseFragment
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFrist3Binding
import com.example.myapplication.databinding.FragmentSecond3Binding

class SecondFragment3 : BaseFragment1WithViewBinding<FragmentSecond3Binding>(
    inflateViewBinding = FragmentSecond3Binding::inflate) {
    private val viewModel by activityViewModels<ShareViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel.liveData.observe(viewLifecycleOwner){it:Int? ->
           binding.text32.text = "${it}"
           Toast.makeText(requireContext(), "SecondFragmentDemo: $it", Toast.LENGTH_SHORT).show()
       }
    }


}