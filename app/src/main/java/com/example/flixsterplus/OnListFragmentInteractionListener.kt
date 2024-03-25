package com.example.flixsterplus

import android.widget.ImageView
import android.widget.TextView

/**
 * This interface is used by the [StarRecyclerViewAdapter] to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by [FragmentStarslist]
 */
interface OnListFragmentInteractionListener {
    fun onItemClick(item:Star, imageView: ImageView, textView: TextView)
}