package com.example.myapplication.demo_API

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.databinding.ActivityDemoApiactivityBinding


class demoAPIActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityDemoApiactivityBinding.inflate(layoutInflater)
    }

    private val viewmodel by viewModels<demoCallapiViewModel>(
        factoryProducer = {
            viewModelFactory {
                addInitializer(demoCallapiViewModel::class){
                    demoCallapiViewModel(servicelocator.todoServic)
                }

            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val policy = ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)

        binding.btn8.setOnClickListener {
            viewmodel.posUserSuspend()
        }

        viewmodel.liveData.observe(this){result ->
           when(result){
               is todoUIstate.error -> {
                   binding.text8.text = "Error: ${result.throwable.message}"
               }
                todoUIstate.loading -> {
                   binding.text8.text = "Loading......"
               }
               is todoUIstate.susecc -> {
                   binding.text8.text = result.todoRespond.title

               }
               is todoUIstate.suseccList ->{
                   binding.text8.text = result.list.joinToString { it.username }
               }
               is todoUIstate.suseccListResponce ->{
                   binding.text8.text = "Post: ${result.user.id} - ${result.user.lang} - ${result.user.name}"
               }
           }

        }
    }
}