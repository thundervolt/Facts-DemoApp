package com.samples.factsdemoapp.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samples.factsdemoapp.R
import com.samples.factsdemoapp.data.model.FactDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fact.view.*

/**
 * FactViewHolder to bind data to list itemView
 *
 * @author AkashG
 * @since 19/07/19.
 */
class FactViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(factDetails: FactDetails?) {
        if (factDetails != null) {
            itemView.txt_title.text = factDetails.title
            itemView.txt_description.text = factDetails.description
            Picasso.get().load(factDetails.imagePath).placeholder(R.drawable.ic_image_placeholder)
                .into(itemView.img_thumbnail)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fact, parent, false)
            return FactViewHolder(view)
        }
    }
}