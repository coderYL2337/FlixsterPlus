package com.example.flixsterplus

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


const val STAR_EXTRA = "STAR_EXTRA"
private const val TAG = "StarAdapter"

class StarRecyclerViewAdapter(private val stars: List<Star>, private val listener: OnListFragmentInteractionListener?) :
    RecyclerView.Adapter<StarRecyclerViewAdapter.StarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return StarViewHolder(view)
    }

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {
        val star = stars[position]
        holder.bind(star)
    }

    override fun getItemCount() = stars.size

    inner class StarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val starNameTextView: TextView = itemView.findViewById(R.id.starName)
        private val starImageView: ImageView = itemView.findViewById(R.id.starImage)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(star: Star) {
            starNameTextView.text = star.name
            starNameTextView.text = star.name
            val imageTransitionName = "transition_starImage"
            val nameTransitionName = "transition_starName"
            starImageView.transitionName = imageTransitionName
            starNameTextView.transitionName = nameTransitionName
            val radius = 100; // corner radius, higher value = more rounded

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${star.profilePath}")
                .placeholder(R.drawable.placeholder_image)
                 .centerCrop() // scale image to fill the entire ImageView
                .transform(RoundedCorners(radius))
                //.centerInside()
                .into(starImageView)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener?.onItemClick(stars[absoluteAdapterPosition],starImageView, starNameTextView)
            }
        }
    }
}


/*class StarRecyclerViewAdapter (private val stars:List<Star>,private val sListener: OnListFragmentInteractionListener? ):
    RecyclerView.Adapter<StarRecyclerViewAdapter.StarViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return StarViewHolder(view)
    }

    inner class StarViewHolder(val sView: View) : RecyclerView.ViewHolder(sView) {
        var sItem: Star? = null
        var sId: Int = 0
        var sStarImageURL:String?=""
        val sStarName:TextView = sView.findViewById(R.id.starName)
        val sStarImage: ImageView = sView.findViewById(R.id.starImage)
        override fun toString(): String {
            return sStarName.toString()+" '"+sStarImageURL.toString()
        }
    }

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {
        // TODO: Get the individual star and bind to holder
        val star = stars[position]
        holder.sItem=star
        holder.sStarName.text=star.name

        Glide.with(holder.sView)
            .load("https://image.tmdb.org/t/p/w500${star.starImageURL}")
            .placeholder(R.drawable.placeholder_image) // placeholder image
            .centerInside()
            .into(holder.sStarImage)

    }
    override fun getItemCount() = stars.size

    }
}*/