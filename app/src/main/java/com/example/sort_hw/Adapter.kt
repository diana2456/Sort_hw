package com.example.sort_hw

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sort_hw.databinding.ItemTextBinding

class Adapter(val onItemClick: (hint: String) -> Unit) : RecyclerView.Adapter<Adapter.TextViewHolder>() {

    private val data = arrayListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: ArrayList<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(
            ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class  TextViewHolder(private val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hint: String) {
            binding.tvText.text = hint
            itemView.setOnClickListener {
                onItemClick(hint)
            }
        }
    }
}