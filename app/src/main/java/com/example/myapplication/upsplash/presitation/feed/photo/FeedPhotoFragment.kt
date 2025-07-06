package com.example.myapplication.upsplash.presitation.feed.photo

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.databinding.FragmentFeedPhotoBinding
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionItemAdapter


class FeedPhotoFragment : BaseFragment1WithViewBinding<FragmentFeedPhotoBinding>(
    inflateViewBinding = FragmentFeedPhotoBinding::inflate,
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


companion object{
    fun  newInstance() = FeedPhotoFragment()
}

}