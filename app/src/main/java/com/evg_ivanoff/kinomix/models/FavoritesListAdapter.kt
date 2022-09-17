package com.evg_ivanoff.kinomix.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FilmListOneItemBinding

//этот адаптер для "избранных"
class FavoritesListAdapter(val listener: Listener) : RecyclerView.Adapter<FavoritesListAdapter.FilmViewHolder>() {

    private var items: List<Film> = listOf()

    class FilmViewHolder(private val binding: FilmListOneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film, listener: Listener) {
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

    fun refresh(items: List<Film>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface Listener {
        fun onItemClick(item: Film)
    }
}