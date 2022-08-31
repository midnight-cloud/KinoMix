package com.evg_ivanoff.kinomix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evg_ivanoff.kinomix.databinding.FilmListOneItemBinding

class FilmListAdapter : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {

//    private val inputData: List<FilmListItem> = listOf()
    private var items: List<FilmListItem> = listOf()

    class FilmViewHolder(private val binding: FilmListOneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilmListItem) {
            binding.apply {
                filmTitle.text = item.totalResults
                filmYear.text = item.response
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
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun refresh(items: List<FilmListItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}