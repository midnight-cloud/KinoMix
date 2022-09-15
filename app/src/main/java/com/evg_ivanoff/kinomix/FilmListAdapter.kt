package com.evg_ivanoff.kinomix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.evg_ivanoff.kinomix.databinding.FilmListOneItemBinding

class FilmListAdapter : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {

//    private val inputData: List<FilmListItem> = listOf()
//    private var items: List<FilmListItem> = listOf()
    private var items: List<FilmListItemDetail> = listOf()

    class FilmViewHolder(private val binding: FilmListOneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilmListItemDetail) {
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

    fun refresh(items: List<FilmListItemDetail>) {
        this.items = items
        notifyDataSetChanged()
    }
}