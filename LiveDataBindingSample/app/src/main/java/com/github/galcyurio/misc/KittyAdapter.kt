package com.github.galcyurio.misc

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.galcyurio.databinding.ItemKittyBinding
import com.github.galcyurio.model.domain.Kitty

/**
 * @author galcyurio
 */
class KittyAdapter : RecyclerView.Adapter<KittyAdapter.ViewHolder>() {
    private val kittyList: MutableList<Kitty> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemKittyBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kitty = kittyList[position]
        holder.bind(kitty)
    }

    override fun getItemCount(): Int = kittyList.size

    fun submitList(kittyList: List<Kitty>) {
        this.kittyList.clear()
        this.kittyList.addAll(kittyList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemKittyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kitty: Kitty) {
            binding.kitty = kitty
            binding.executePendingBindings()
        }
    }
}