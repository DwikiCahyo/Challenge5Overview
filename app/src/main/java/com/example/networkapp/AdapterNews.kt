package com.example.networkapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkapp.databinding.ItemNewsBinding
import com.example.networkapp.model.Movie
import com.example.networkapp.network.ResultsItem

class AdapterNews(var listMovie : List<ResultsItem>):RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    class ViewHolder (var binding : ItemNewsBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val list = listMovie[position]
        holder.binding.tvTitle.text = list.originalTitle
        holder.binding.tvTanggal.text = list.releaseDate
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w200/${list.posterPath}").fitCenter().into(holder.binding.ivImageNews)

        holder.binding.cvDetailMoview.setOnClickListener {

            val title = list.title
            val date = list.releaseDate
            val overview = list.overview
            val image = list.posterPath

            val movieData = Movie(title,overview,date,image)
            val Intent = Intent(holder.itemView.context, DetailActivity::class.java)
            Intent.putExtra("data_movie",movieData)
            holder.itemView.context.startActivity(Intent)

        }
    }
}
