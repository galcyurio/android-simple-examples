package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.galcyurio.roomtodo.databinding.FragmentAddTaskBinding

/**
 * @author galcyurio
 */
internal class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
}