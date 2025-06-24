package com.example.myapplication.gson_moshi.okhttp_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDemookhttpBinding

class demookhttpActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityDemookhttpBinding.inflate(layoutInflater)
    }
    private val vm by viewModels<okhttpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        vm.uistate.observe(this){state ->
            when(state){
                is DemookhttpUistate.idle ->{
                    binding.text.text = "idle"
                }
                is DemookhttpUistate.Loading ->{
                    binding.text.text = "Loading......"
                }
                is DemookhttpUistate.Content ->{
                    binding.text.text = "Content: ${state.todos}"
                }
                is DemookhttpUistate.Error -> {
                    binding.text.text = "Error: ${state.throwable}"
                }

            }


        }
        binding.btn.setOnClickListener {
            vm.getTodos2()
        }
    }
}