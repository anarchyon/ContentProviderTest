package project.paveltoy.contentprovidertest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.paveltoy.contentprovidertest.databinding.ItemMovieBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.BaseViewHolder>() {
    var dataSet = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    class BaseViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieName: String) {
            binding.movieName.text = movieName
        }
    }
}