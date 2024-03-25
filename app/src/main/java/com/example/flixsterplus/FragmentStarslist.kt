package com.example.flixsterplus

import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterplus.BuildConfig.FLIXSTER_API_KEY
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Headers
import org.w3c.dom.Text



class FragmentStarslist:Fragment(),OnListFragmentInteractionListener {
    private val adapterPosition: Int=0
    private lateinit var StarRecyclerView: RecyclerView
    private val stars = mutableListOf<Star>()

      override fun onCreateView(inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?):View?{

        val view = inflater.inflate(R.layout.fragment_view,container,false)
          return view
      }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your RecyclerView here
        StarRecyclerView = view.findViewById(R.id.list_stars) as RecyclerView
        StarRecyclerView.layoutManager = GridLayoutManager(context,2)

        // Assuming progressBar is also a view you need to initialize
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar

        // Now it's safe to fetch data
        fetchTrendingPeople(progressBar)
    }


    private fun fetchTrendingPeople(progressBar: ContentLoadingProgressBar) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = FLIXSTER_API_KEY

        //var imagePoster =KnownFor().posterPath
        //"https://image.tmdb.org/t/p/w500${imagePoster?.firstOrNull { KnownFor().posterPath != null }?: ""}"
        client["https://api.themoviedb.org/3/trending/person/day?language=en-US",params,object:JsonHttpResponseHandler() {


            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                progressBar.hide()
                val gson = Gson()
                try {
                    val trendingPeopleResponse = gson.fromJson(json?.jsonObject.toString(), TrendingPeopleResponse::class.java)
                    stars.clear()
                    stars.addAll(trendingPeopleResponse.results)
                    StarRecyclerView.adapter = StarRecyclerViewAdapter(stars, this@FragmentStarslist)
                } catch (e: JsonSyntaxException) {
                    Log.e("FragmentStarslist", "Failed to parse JSON", e)
                }
            }




            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                progressBar.hide()
                Log.e("FragmentStarslist", errorResponse)
            }


        }]
    }


    override fun onItemClick(item: Star,imageView:ImageView, textView:TextView) {

        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(STAR_EXTRA, item)}

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            androidx.core.util.Pair(imageView, "transition_starImage"),
            androidx.core.util.Pair(textView, "transition_starName")
        )

        startActivity(intent, options.toBundle())
    }
}



/*fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}*/



/*  //TODO - Parse JSON into Models
  val resultsJSON : JSONArray = json?.jsonObject?.getJSONArray("results")
  val starsRawJSON : String = resultsJSON.toString()
  val gson = Gson()
  val arrayStarType = object: TypeToken<List<Star>>()
  {}.type
  val models : List<Star> = gson.fromJson(starsRawJSON,arrayStarType)
  StarRecyclerView.adapter = StarRecyclerViewAdapter(models, this@FragmentStarslist)

*/

/*override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
             progressBar.hide()
             val resultsJSON : JSONArray? = json?.jsonObject?.getJSONArray("results")
             val starsRawJSON : String = resultsJSON.toString()
             val gson = Gson()
             val arrayStarType = object: TypeToken<List<Star>>()
             {}.type
             val stars : List<Star> = gson.fromJson(starsRawJSON,arrayStarType)
             Log.d("Success", "Successfully fetched stars: $json")
             StarRecyclerView.adapter = StarRecyclerViewAdapter(stars, this@FragmentStarslist)
             // StarRecyclerViewAdapter.notifyDataSetChanged()
         }*/

/*
         json?.jsonObject?.getJSONArray("results")?.let { resultsJson ->
          val starList = mutableListOf<Star>()
          for (i in 0 until resultsJson.length()) {
              val starJson = resultsJson.getJSONObject(i)
              val knownForList = mutableListOf<KnownFor>()
              val knownForJson = starJson.getJSONArray("known_for")
              if (knownForJson.length() > 0) {
                  val knownForObj = knownForJson.getJSONObject(0)
                  knownForList.add(
                      KnownFor(
                          knownForObj.getString("title"),
                          knownForObj.getString("overview"),
                          knownForObj.getString("poster_path")
                      )
                  )
              }
              starList.add(
                  Star(
                      starJson.getInt("id"),
                      starJson.getString("name"),
                      starJson.getString("profile_path"),
                      knownForList
                  )
              )
          }
             stars.clear()
             stars.addAll(starList)
             if (StarRecyclerView.adapter == null) {
                 StarRecyclerView.adapter = StarRecyclerViewAdapter(stars, this@FragmentStarslist)
             } else {
                 // Here we notify the adapter that the data set has changed so it can update the RecyclerView.
                 StarRecyclerView.adapter?.notifyDataSetChanged()
             }
         }
*/


/* override fun onItemClick(item: Star) {

     val intent = Intent(requireContext(), DetailActivity::class.java)
     intent.putExtra(STAR_EXTRA, item)
     startActivity(intent)
 }*/



/*override fun onItemClick(item: Star){
val intent = Intent(context, DetailActivity::class.java)
val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
    context,
    Pair.create(starImageView, "transition_starImage"),
    Pair.create(starNameTextView, "transition_starName")
)
startActivity(intent, options.toBundle())
}*/
/* override fun onItemClick(item: Star) {
     val intent = Intent(context, DetailActivity::class.java).apply {
         putExtra(STAR_EXTRA, item)
     }
     startActivity(intent)
 }
 */
       // ActivityOptionsCompat options = ActivityOptionsCompat.
       // makeSceneTransitionAnimation(MainActivity.this, (View)ivProfile, "person")



   /*override fun onItemClick(item:Star){
      // val star =stars[absoluteAdapterPosition]

       // TODO: Navigate to Details screen and pass selected star
       val intent =Intent(context, DetailActivity::class.java)
       intent.putExtra(STAR_EXTRA,item)
       context?.startActivity(intent)*/

/*    val gson = Gson()
                json?JSONObject.let {
                    val trendingPeopleResponse = gson.fromJson(it.toString(), TrendingPeopleResponse::class.java)
                    stars.clear()
                    stars.addAll(trendingPeopleResponse.results)
                    StarRecyclerView.adapter = StarRecyclerViewAdapter(stars, this@FragmentStarslist)
                }

               */







