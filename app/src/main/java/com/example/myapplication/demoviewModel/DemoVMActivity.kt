package com.example.myapplication.demoviewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDemoVmactivityBinding

class DemoVMActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
       ActivityDemoVmactivityBinding.inflate(layoutInflater)
    }
    private val myViewModel by viewModels<MyViewModel>(
        factoryProducer ={
            viewModelFactory {
                addInitializer(MyViewModel::class){//Đăng ký cách khởi tạo MyViewModel.
                    MyViewModel(MyRepository())  //Tham số MyViewModel::class giúp Factory biết bạn đang tạo loại ViewModel nào.
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // val viewModelFatory = MyViewModelFactory(MyRepository())
      //class MyViewModelFactory chỉ là cách 1 không dùng cũng được, thay bằng c2
        //factoryProducer ={
        //            viewModelFactory {
        //                addInitializer(MyViewModel::class){
        //                    MyViewModel(MyRepository())
        //                }
        //            }
        //        }

        binding.btn.setOnClickListener {
            myViewModel.getDataFactory()
        }
        myViewModel.liveData.observe(this){result:String? ->
            binding.text.text = "${result}"
        }
    }
}