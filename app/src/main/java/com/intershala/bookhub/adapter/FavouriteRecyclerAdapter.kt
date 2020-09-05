


//3.

package com.intershala.bookhub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.intershala.bookhub.R
import com.intershala.bookhub.activity.DescriptionActivity
import com.intershala.bookhub.database.BookEntity
import com.squareup.picasso.Picasso

class FavouriteRecyclerAdapter (val context : Context, val bookList : List<BookEntity>) : RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>() {

    class FavouriteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val txtBookName : TextView = view.findViewById(R.id.txtFavBookTitle)
        val txtBookAuthor : TextView = view.findViewById(R.id.txtFavBookAuthor)
        val txtBookPrice : TextView = view.findViewById(R.id.txtFavBookPrice)
        val txtBookRating : TextView = view.findViewById(R.id.txtFavBookRating)
        val imgBookImage : ImageView = view.findViewById(R.id.imgFavBookImage)
        val llContent : LinearLayout = view.findViewById(R.id.llFavContent)

        //12. adding setOnClickListener to the recyclerView item
        val llFavContent : LinearLayout = view.findViewById(R.id.llFavContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        //inflate the fragment and return the view which the Favourite viewHolder holds
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_favourites_single_row, parent, false)

        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int){
        //attaching the views here
        val book = bookList[position]

        holder.txtBookName.text = book.bookName
        holder.txtBookAuthor.text = book.bookAuthor
        holder.txtBookPrice.text = book.bookPrice
        holder.txtBookRating.text = book.bookRating
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

        //12.adding clickListener t+o it
        holder.llFavContent.setOnClickListener{
            val intent = Intent(context, DescriptionActivity::class.java)
            val id = book.book_id.toString()
            intent.putExtra("book_id", id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

}