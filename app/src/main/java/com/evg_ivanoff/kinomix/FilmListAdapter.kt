package com.evg_ivanoff.kinomix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evg_ivanoff.kinomix.databinding.FilmListOneItemBinding

class FilmListAdapter : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {

//    private val inputData: List<FilmListItem> = listOf()
//    private var items: List<FilmListItem> = listOf()
    private var items: List<String> = listOf()

    class FilmViewHolder(private val binding: FilmListOneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
//                filmTitle.text = item.totalResults
                filmTitle.text = item
                filmYear.text = "2020"
//                filmYear.text = item.response
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

    fun refresh(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }
}