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
import com.example.myapplication.upsplash.domain.model.CollectionItem
import kotlinx.coroutines.flow.combine


//DiffUtil.ItemCallback(vẽ lại item cần thay đổi) thay cho notifydatasetchange( vẽ lại toàn bộ rcv)

object collectionsitemCallBack: DiffUtil.ItemCallback<FeedCollectionUiState.CollectionsItem>() {
    override fun areItemsTheSame(
        oldItem: FeedCollectionUiState.CollectionsItem,
        newItem: FeedCollectionUiState.CollectionsItem
    )=
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: FeedCollectionUiState.CollectionsItem,
        newItem: FeedCollectionUiState.CollectionsItem
    )=
        oldItem == newItem

}

// ListAdapter thay cho Recycleview.Adapter vì ListAdapter hỗ trợ DiffUtil.ItemCallback

class FeedCollectionItemAdapter(
    val onClick: (item : FeedCollectionUiState.CollectionsItem) -> Unit,
    val requestManager: RequestManager,
): ListAdapter<FeedCollectionUiState.CollectionsItem, FeedCollectionItemAdapter.VH>(
    collectionsitemCallBack
){
    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCollectionLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

   inner class VH(val binding: ItemCollectionLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                adapterPosition.let {pos ->
                    if (pos != RecyclerView.NO_POSITION){
                        onClick(getItem(pos))
                    }
                }
            }
        }


        fun bind(item : FeedCollectionUiState.CollectionsItem){
            binding.textTitle.text = item.title
            binding.textDescription.text = item.description

            requestManager.load(item.photocover)
                .centerCrop()
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)
        }
    }
}




//getItem : Hàm có sẵn của ListAdapter.



