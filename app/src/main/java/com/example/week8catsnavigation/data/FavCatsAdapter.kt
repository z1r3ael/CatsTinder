package com.example.week8catsnavigation.data

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week8catsnavigation.R
import com.facebook.drawee.view.SimpleDraweeView

class FavCatsAdapter(private val favCats: ArrayList<CatData>) : RecyclerView.Adapter<FavCatsAdapter.CatViewHolder>() {

    inner class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catImageView: SimpleDraweeView = view.findViewById(R.id.catImageView) as SimpleDraweeView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_image_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem: CatData = favCats[position]
        val imageUrl = catItem.catImageUrl
        val uri = Uri.parse(imageUrl)
        holder.catImageView.setImageURI(uri)
    }

    override fun getItemCount(): Int {
        return favCats.size
    }
}