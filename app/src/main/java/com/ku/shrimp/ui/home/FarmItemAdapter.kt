package com.ku.shrimp.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ku.shrimp.R
import kotlinx.android.synthetic.main.item_farm.view.*
import kotlin.math.log

class FarmItemAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<FarmItemAdapter.ViewHolder>()
{

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.apply {
            getNameTxt.text = items[pos]
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_farm, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val getNameTxt: TextView = view.textView_farm
    }
}

