package com.evg_ivanoff.kinomix.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FilmListOneItemBinding

class FilmListAdapter(val listener: Listener) : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {

    private var items: List<FilmListItemDetail> = listOf()

    class FilmViewHolder(private val binding: FilmListOneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilmListItemDetail, listener: Listener) {
            binding.apply {
                filmPoster.load(item.poster){
                    crossfade(true)
                    placeholder(R.drawable.image_loading)
                    error(R.drawable.image_not_found)
                    transformations(CircleCropTransformation())
                }
                filmTitle.text = item.title
                filmYear.text = item.year
                filmStyle.text = item.type
            }
            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = FilmListOneItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size

    fun refresh(items: List<FilmListItemDetail>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface Listener {
        fun onItemClick(item: FilmListItemDetail)
    }
}