package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.mvvm.ApiClient
import com.example.myapplication.mvvm.CollectionAdapter
import com.example.myapplication.mvvm.CollectionRepository
import com.example.myapplication.mvvm.CollectionViewModel
import com.example.myapplication.mvvm.CollectionViewModelFactory
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionItemAdapter
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionUiState
import com.example.myapplication.upsplash.presitation.feed.collection.feedcollectionViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: CollectionViewModel
    private lateinit var adapter: FeedCollectionItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
//        recycler.layoutManager = LinearLayoutManager(this)
//
//        val api = ApiClient.unsplashApi
//        val repository = CollectionRepository(api)
//        val factory = CollectionViewModelFactory(repository)
//
//        viewModel = ViewModelProvider(this, factory)[CollectionViewModel::class.java]
//
//        viewModel.collections.observe(this) { collections ->
//            recycler.adapter = CollectionAdapter(collections)
//        }
//
//        viewModel.error.observe(this) {
//            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
//        }
//
//        viewModel.loadCollections()

    }
}