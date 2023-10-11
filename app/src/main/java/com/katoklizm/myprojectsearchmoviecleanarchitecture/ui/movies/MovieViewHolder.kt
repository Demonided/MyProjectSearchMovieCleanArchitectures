package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

class MovieViewHolder(
    parent: ViewGroup,
    private val clickListener: MoviesAdapter.MovieClickListener
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)
    ) {

    var cover: ImageView = itemView.findViewById(R.id.cover)
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)

    var inFavoriteToggle: ImageView = itemView.findViewById(R.id.favorite)

    fun bind(movie: Movie) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.title
        description.text = movie.description



        inFavoriteToggle.setImageDrawable(getFavoriteToggleDrawable(movie.inFavorite))

        itemView.setOnClickListener { clickListener.onMovieClick(movie = movie) }

        inFavoriteToggle.setOnClickListener { clickListener.onFavoriteToggleClick(movie) }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getFavoriteToggleDrawable(inFavorite: Boolean): Drawable? {
        return itemView.context.getDrawable(
            if (inFavorite) R.drawable.active_favorire else R.drawable.inactive_favorire
        )
    }
}