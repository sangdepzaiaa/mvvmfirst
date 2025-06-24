package com.example.myapplication.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.core.view.OneShotPreDrawListener.add
import com.example.myapplication.R

import com.example.myapplication.databinding.ActivityDemoFragmentBinding

class DemoFragment : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDemoFragmentBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // savedInstanceState là một Bundle?
        // chứa dữ liệu về trạng thái trước đó của Activity. Nếu nó là null , Activity sẽ bắt đầu lại từ đầu.
        if (savedInstanceState === null){
            //supportFragmentManager được sử dụng để tương thích với các phiên bản Android cũ hơn
            // (thông qua thư viện AndroidX).commit xác định bắt đầu giao dịch Fragment.
            supportFragmentManager.commit {
//cho phép xử lý tối ưu trạng thái và hoạt ảnh của Fragment.
                setReorderingAllowed(true) // Optimize
                add(
                    binding.demofrag.id,
                    FirstFragment.newinstain(count = 100),
                    FirstFragment::class.java.name,
                )
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("###","findFragmentById:" + supportFragmentManager.findFragmentById(binding.demofrag.id)) // màn đang hiển thị
        val secondFragment = supportFragmentManager.findFragmentByTag(SecondFragment::class.java.name) as? SecondFragment
        Log.d("###","findFragmentByTag(SecondFragment): $secondFragment") // maàn second
    }
}