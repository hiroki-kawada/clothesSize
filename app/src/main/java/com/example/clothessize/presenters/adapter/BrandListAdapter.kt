package com.example.clothessize.presenters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothessize.databinding.BrandListItemBinding
import com.example.clothessize.model.BlandData
import com.example.clothessize.presenters.viewmodel.BrandListViewModel

private object DiffCallback : DiffUtil.ItemCallback<BlandData>() {
    override fun areItemsTheSame(oldItem: BlandData, newItem: BlandData): Boolean {
        return oldItem.brand_key == newItem.brand_key
    }

    override fun areContentsTheSame(oldItem: BlandData, newItem: BlandData): Boolean {
        return oldItem == newItem
    }

}

class BrandListAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val brandListViewModel: BrandListViewModel
) : ListAdapter<BlandData, BrandListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: BrandListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: BlandData,
            viewLifecycleOwner: LifecycleOwner,
            viewModel: BrandListViewModel
        ) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                brandData = item
                this.viewModel = viewModel
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(BrandListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, brandListViewModel)
    }

}