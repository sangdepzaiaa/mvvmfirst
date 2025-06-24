package com.example.myapplication.Fragments2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFrist2Binding


class FristFragment2 : BaseFragment2<FragmentFrist2Binding>(
    infla = FragmentFrist2Binding::inflate,
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = requireArguments().getInt(CON)
        binding.txvText.text = "count = ${count}"
    }
    companion object{
        const val CON ="count"
        fun newIntain( count :Int): FristFragment2{
            return FristFragment2().apply {
                this.arguments = bundleOf(
                    CON to count
                )
            }
        }
    }
}