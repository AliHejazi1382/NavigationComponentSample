package com.example.navigationcomponentsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponentsample.databinding.RecyclerviewItemsLayoutBinding
import com.example.navigationcomponentsample.event.OnItemClickListener
import com.example.navigationcomponentsample.extensions.getImageResource
import com.example.navigationcomponentsample.extensions.responsiveItem
import com.example.navigationcomponentsample.model.Flowers

class FlowerAdapter(
    private val context: Context,
    flowerCallback: FlowerCallback,
    private val listener: OnItemClickListener) :
    ListAdapter<Flowers, FlowerAdapter.ViewHolder>(flowerCallback) {
    private var _binding: RecyclerviewItemsLayoutBinding? = null
    private val binding: RecyclerviewItemsLayoutBinding get() = _binding!!

    class ViewHolder(
        private val binding: RecyclerviewItemsLayoutBinding,
        private val listener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(context: Context, name: String?, photo: String?) {
            binding.lblName.text = name
            binding.imgPhoto.setImageResource(getImageResource(context, photo))
            responsiveItem(binding.imgPhoto)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position == RecyclerView.NO_POSITION) return

            listener.onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerviewItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
                ),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFlower = getItem(position)
        holder.bind(
            context,
            currentFlower.name,
            currentFlower.photo
        )
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        _binding = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    fun getFlowerAt(position: Int): Flowers = getItem(position)

    class FlowerCallback : DiffUtil.ItemCallback<Flowers>() {
        override fun areItemsTheSame(oldItem: Flowers, newItem: Flowers): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Flowers, newItem: Flowers): Boolean {
            return oldItem == newItem
        }

    }
}