package com.samples.factsdemoapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samples.factsdemoapp.data.model.FactDetails
import com.samples.factsdemoapp.ui.viewholder.FactViewHolder

/**
 * Facts list adapter
 *
 * @author AkashG
 * @since 19/07/19.
 */
class FactsListAdapter : RecyclerView.Adapter<FactViewHolder>() {

    var factsList: List<FactDetails>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return factsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int): FactDetails? {
        return factsList?.get(position)
    }

    /**
     * Update facts list
     */
    fun updateFactsList(factsList: List<FactDetails>) {
        this.factsList = factsList
        notifyDataSetChanged()
    }
}