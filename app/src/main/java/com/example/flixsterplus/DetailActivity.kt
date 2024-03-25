package com.example.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DetailActivity : AppCompatActivity() {
    private lateinit var starImageView: ImageView
    private lateinit var starNameTextView: TextView
    private lateinit var knownForTitleTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var posterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_view)

        starImageView = findViewById(R.id.starImageDetail)
        starNameTextView = findViewById(R.id.starnameDetail)
        knownForTitleTextView = findViewById(R.id.knownforTitle)
        overviewTextView = findViewById(R.id.overviewDetail)
        posterImageView = findViewById(R.id.posterImage)

       // val star = intent.getSerializableExtra(STAR_EXTRA) as Star?:return
        val star = intent.getSerializableExtra(STAR_EXTRA) as Star?
        star?.let {
           starNameTextView.text = star.name
           knownForTitleTextView.text = star.knownFor.firstOrNull()?.title
           overviewTextView.text = star.knownFor.firstOrNull()?.overview
            starImageView.transitionName = intent.getStringExtra("TRANSITION_NAME_IMAGE")
            starNameTextView.transitionName = intent.getStringExtra("TRANSITION_NAME_TEXT")

            val radius = 100;
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.profilePath}")
                .placeholder(R.drawable.placeholder_image)
                //.centerInside()
                .centerCrop() // scale image to fill the entire ImageView
                .transform(RoundedCorners(radius))
                .into(starImageView)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.knownFor.firstOrNull()?.posterPath}")
                .placeholder(R.drawable.placeholder_image)
                //.centerInside()
                .centerCrop() // scale image to fill the entire ImageView
                .transform(RoundedCorners(radius))
                .into(posterImageView)
        } ?: run {
            // Handle the case where the Star object is null
            // You might want to close the activity or show an error message
            finish() // Close the activity as there's no data to show
        }

        /*starNameTextView.text = star.name
        knownForTitleTextView.text = star.knownFor.firstOrNull()?.title
        overviewTextView.text = star.knownFor.firstOrNull()?.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${star.profilePath}")
            .placeholder(R.drawable.placeholder_image)
            .centerInside()
            .into(starImageView)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${star.knownFor.firstOrNull()?.posterPath}")
            .placeholder(R.drawable.placeholder_image)
            .centerInside()
            .into(posterImageView)
            */

    }
}