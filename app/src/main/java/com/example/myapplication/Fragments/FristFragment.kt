package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFristBinding


class FirstFragment : BaseFragment1WithViewBinding<FragmentFristBinding>(
    inflateViewBinding = FragmentFristBinding::inflate // ::inflate: tham chiếu đến hàm inflate
//    @JvmStatic
//    fun inflate(
//        inflater: LayoutInflater,
//        parent: ViewGroup?,
//        attachToParent: Boolean
//    ): FragmentFristBinding

// được tạo
// sẵn cho mỗi viewBinding, ở đây là FragmentFristBinding
    // <=> val inflateViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFristBinding =
    //    FragmentFristBinding::inflate
) {
    var count2  = 0 // unsave khi chết tiến trình và xoay màn hình
    var saveState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveState = savedInstanceState?.getInt("savedstate") ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("savedstate",saveState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//requireArguments() : Trả về Bundle đối số của Fragment , ném ra IllegalStateException nếu arguments là null .
// Điều này giả định rằng Fragment được tạo bằng Bundle không null (được đặt trong phương thức newInstance ).


        var count = requireArguments().getInt(COUNT_KEY)
        binding.text.text = "count = ${count}, ${count2}, ${saveState}"
        binding.btnsavestate.setOnClickListener {
            ++saveState
        }
        binding.btn.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                // chuyển qua frag second luôn , vì không có addtobackstack neen frag first bị chết luôn
                replace<SecondFragment>(
                    containerViewId = R.id.demofrag,
                    tag = SecondFragment::class.java.name,
                    args = bundleOf(
                        "name" to "RxMobileTeam",
                        "age" to 30,
                        "isDeveloper" to true,
                        "languages" to arrayListOf("Java", "Kotlin", "Swift", "Objective-C"),
                       // "person" to Person(name = "Kris Garner", age = 1163),
                    )
                )
                addToBackStack("first_to_second")
            }
        }
    }
    companion object{
        const val COUNT_KEY = "count"
        fun newinstain(count : Int) : FirstFragment{
            return FirstFragment().apply {
 //   this.arguments : Thuộc tính arguments của Fragment là một Bundle có thể lưu trữ các cặp khóa-giá trị để truyền dữ liệu cho Fragment.
 //bundleOf(...) : Một hàm mở rộng Kotlin Android tạo ra một Bundle với các cặp khóa-giá trị được chỉ định.
                this.count2 = count
                this.arguments = bundleOf(

                    COUNT_KEY to count//Ghép nối COUNT_KEY (tức là "count" ) với tham số số nguyên đếm , lưu trữ nó trong Bundle .
                    //Hiệu ứng : Giá trị đếm được đóng gói và đính kèm vào Fragment, giúp có thể truy xuất trong onViewCreated .
                )
            }
        }
    }


}