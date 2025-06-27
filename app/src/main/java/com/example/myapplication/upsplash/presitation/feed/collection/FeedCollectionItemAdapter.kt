package com.example.myapplication.upsplash.presitation.feed.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.ItemCollectionLayoutBinding
import kotlinx.coroutines.flow.combine


//DiffUtil.ItemCallback(vẽ lại item cần thay đổi) thay cho datasetchange( vẽ lại toàn bộ rcv)
object CollectionItemDiffUtilItemCallback: DiffUtil.ItemCallback<FeedCollectionUiState.CollectionsItem>() {
    override fun areItemsTheSame(oldItem: FeedCollectionUiState.CollectionsItem, newItem: FeedCollectionUiState.CollectionsItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FeedCollectionUiState.CollectionsItem, newItem: FeedCollectionUiState.CollectionsItem) =
       oldItem == newItem
}

// ListAdapter thay cho Recycleview.Adapter vì ListAdapter hỗ trợ DiffUtil.ItemCallback
class FeedCollectionItemAdapter(
    private val onItemClick: (item : FeedCollectionUiState.CollectionsItem) -> Unit,
    private val requestManager:  RequestManager)
    : ListAdapter<FeedCollectionUiState.CollectionsItem, FeedCollectionItemAdapter.VH>(
    CollectionItemDiffUtilItemCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCollectionLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

//getItem : Hàm có sẵn của ListAdapter.
    override fun onBindViewHolder(holder: VH, position: Int): Unit = holder.bind(getItem(position))

   inner class VH(val binding: ItemCollectionLayoutBinding): ViewHolder(binding.root){
       init {
           itemView.setOnClickListener {
               adapterPosition.let { pos ->
                   if (pos != RecyclerView.NO_POSITION){
                       onItemClick(getItem(pos))
                   }

               }
           }
       }
        fun bind(item: FeedCollectionUiState.CollectionsItem){
            binding.run{
                textTitle.text = item.title
                textDescription.text = item.description

                requestManager.load(item.photocover)
                    .fitCenter()
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

            }
        }
    }
}


