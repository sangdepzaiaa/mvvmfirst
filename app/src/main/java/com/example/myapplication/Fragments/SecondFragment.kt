package com.example.myapplication.Fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSecondBinding


class SecondFragment :BaseFragment1WithViewBinding<FragmentSecondBinding>(
    inflateViewBinding = FragmentSecondBinding::inflate,
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnback.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btn3.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                // chuyển qua frag second luôn , vì không có addtobackstack neen frag first bị chết luôn
                replace<ThirdFragment>(
                    containerViewId = R.id.demofrag,
                    tag = ThirdFragment::class.java.name,
                    args = bundleOf(
                        "name" to "RxMobileTeam",
                        "age" to 30,
                        "isDeveloper" to true,
                        "languages" to arrayListOf("Java", "Kotlin", "Swift", "Objective-C"),
                        // "person" to Person(name = "Kris Garner", age = 1163),
                    )
                )
                addToBackStack("second_to_third")
            }
        }

    }


}