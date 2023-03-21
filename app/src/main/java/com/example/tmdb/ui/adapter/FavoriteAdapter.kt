package com.example.tmdb.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.ItemFavoriteBinding
import com.example.tmdb.databinding.ItemMovieBinding
import com.example.tmdb.networking.entity.Movie

class FavoriteAdapter(
    val context: Context,
    productsList:  List<Movie>
) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var listData: List<Movie>? = productsList
    private lateinit var binding: ItemFavoriteBinding



    class MyViewHolder(binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        binding.movieModel = listData!![position]

    }

    override fun getItemCount(): Int {

        return if (listData == null) 0
        else listData!!.size
    }


}