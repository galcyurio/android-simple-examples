package com.github.galcyurio.ormlitetodo.misc

import com.github.galcyurio.ormlitetodo.data.Task

class Events {
    class TaskCreated
    class TaskDoneChanged(val task: Task)
    class TaskItemLongClicked(val task: Task)
}