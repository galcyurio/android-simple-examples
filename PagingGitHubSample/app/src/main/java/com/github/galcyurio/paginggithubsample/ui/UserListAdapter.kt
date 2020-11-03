package com.github.galcyurio.paginggithubsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.galcyurio.paginggithubsample.domain.User

/**
 * @author galcyurio
 */
class UserListAdapter : PagedListAdapter<User, UserListAdapter.ViewHolder>(
    UserDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txt: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(user: User) {
            txt.text = user.name
        }
    }
}