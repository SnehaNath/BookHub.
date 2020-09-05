package com.intershala.bookhub.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.intershala.bookhub.R
import com.intershala.bookhub.adapter.FavouriteRecyclerAdapter
import com.intershala.bookhub.database.BookDatabase
import com.intershala.bookhub.database.BookEntity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavouritesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerFavourite : RecyclerView
    lateinit var progressLayout : RelativeLayout
    lateinit var progressBar : ProgressBar
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var recyclerAdapter: FavouriteRecyclerAdapter


    var dbBookList = listOf<BookEntity>()         //8. Store the book list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerFavourite = view.findViewById(R.id.recyclerFavourite)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE


        layoutManager = GridLayoutManager(activity as Context , 2 )             //2-> items in a row

        //6. before initialising the adapter we'll need to get the list of favourite books -> retrieving list from DB

        //9. now getting the data
        dbBookList = RetrieveFavourites(activity as Context).execute().get()
        /*   applicationContext is not given here, bc fragment can't access then applicationContext -> WHY?   */

        //10. after getting list -> check the list is not empty and activity hosting the fragment is not empty -> true:initialise the adapter and display the recyclerView
        if(activity != null) {              //dbBookList != null -> always true
            progressLayout.visibility = View.GONE
            recyclerAdapter = FavouriteRecyclerAdapter(activity as Context, dbBookList)
            recyclerFavourite.adapter = recyclerAdapter
            recyclerFavourite.layoutManager = layoutManager
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class RetrieveFavourites (val context : Context) : AsyncTask<Void, Void, List<BookEntity>>()  {
        override fun doInBackground(vararg p0: Void?): List<BookEntity> {
            val db = Room.databaseBuilder(context, BookDatabase::class.java, "book-db").build()

            return db.bookDao().getAllBooks()

            //7. to get a list of book entities -> we need to 1st use the AsyncTask class, then execute it and then use get() method to obtain the result
        }
    }

}


