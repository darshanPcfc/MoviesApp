package com.example.moviesapp.ui.moduleone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieRowBinding
import com.example.moviesapp.ni.remote.response.Results
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.IMovieItemListener

class MoviesAdapter(var iMovieItemListener: IMovieItemListener): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var items : ArrayList<Results> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieRowBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    fun setMoviesList(items: ArrayList<Results>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results) {
            binding.item = item
            binding.imgMovie.setOnClickListener{
                iMovieItemListener.onMovieItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
}