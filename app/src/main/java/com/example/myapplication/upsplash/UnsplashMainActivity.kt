package com.example.myapplication.upsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityUnsplashMainBinding
import com.example.myapplication.upsplash.presitation.feed.FeedsFragment
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionFragment

class UnsplashMainActivity : AppCompatActivity(){
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityUnsplashMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FeedsFragment>(
                    containerViewId = R.id.container,
                    tag = FeedsFragment::class.java.name
                )
            }
        }
    }
}