package com.example.myapplication.Fragments2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.myapplication.Fragments.FirstFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDemofragBinding

class Demofrag : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
       ActivityDemofragBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState === null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(
                    binding.demofrag2.id,
                    FristFragment2.newIntain(count = 100),
                    FirstFragment::class.java.name,
                )
            }
        }
    }
}