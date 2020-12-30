package com.example.projetkotlin.presentation.view

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetkotlin.presentation.model.Pokemon


class ListAdapter(
    private val values: List<Pokemon>,
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Pokemon?)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var txtHeader: TextView
        var txtFooter: TextView
        var layout: View

        init {
            layout = v
            txtHeader = v.findViewById(R.id.firstLine)
            txtFooter = v.findViewById(R.id.secondLine)
        }
    }

    fun add(position: Int, item: Pokemon) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentPokemon = values[position]
        holder.txtHeader.text = currentPokemon.name
        holder.txtFooter.text = currentPokemon.url
        holder.itemView.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                listener.onItemClick(currentPokemon)
            }
        })
    }

    override fun getItemCount(): Int {
        return values.size
    }

}