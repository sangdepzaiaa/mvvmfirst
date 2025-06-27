package com.example.myapplication.upsplash.presitation.search.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchUserBinding

class SearchUserFragment : BaseFragment1WithViewBinding<FragmentSearchUserBinding>(
    inflateViewBinding = FragmentSearchUserBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        fun instance() = SearchUserFragment()
    }

}