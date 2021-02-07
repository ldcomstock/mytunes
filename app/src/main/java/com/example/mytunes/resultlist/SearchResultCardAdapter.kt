package com.example.mytunes.resultlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytunes.databinding.SearchResultCardBinding
import com.example.mytunes.resultlist.model.SearchResultItem

class SearchResultCardAdapter(private var results: List<SearchResultItem>) :
    RecyclerView.Adapter<SearchResultCardAdapter.SearchResultCardViewHolder>() {

    class SearchResultCardViewHolder(private var binding: SearchResultCardBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: SearchResultItem) {
            binding.result = result
            binding.executePendingBindings()
        }
    }

    fun setData(newResults: List<SearchResultItem>) {
        results = newResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultCardViewHolder {
        return SearchResultCardViewHolder(SearchResultCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchResultCardViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)

    }

    override fun getItemCount(): Int {
        return results.size
    }
}