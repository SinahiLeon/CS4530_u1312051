package com.example.roomdemov2

import com.example.roomdemov2.room.TaskDaoInterface
import com.example.roomdemov2.room.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository(val scope: CoroutineScope,
                 private val dao: TaskDaoInterface){

    //access the operations from my DAO

    // representing all the tasks that i have
    val allTasks: Flow<List<TaskEntity?>> =
        dao.getAllTasks()

    // Talk to some external API

    fun addTask(task: String){
        scope.launch {
            val taskObj = TaskEntity(task)
            dao.insertTask(taskObj)
        }
    }
}