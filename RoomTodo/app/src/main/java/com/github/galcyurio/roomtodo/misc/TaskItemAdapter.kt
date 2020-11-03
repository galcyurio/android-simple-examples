package com.github.galcyurio.roomtodo.misc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.data.domain.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {
    private val tasks: MutableList<Task> by lazy { mutableListOf<Task>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.itemView) {
            txtNumber.text = (position + 1).toString()
            txtTask.text = task.task
            txtDetail.text = task.detail
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTasks(tasks: List<Task>) {
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}