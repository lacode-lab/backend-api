package com.kurage.lab.backendapi.controller

import com.kurage.lab.backendapi.model.CreateTaskRequest
import com.kurage.lab.backendapi.model.Task
import com.kurage.lab.backendapi.model.UpdateTaskRequest
import com.kurage.lab.backendapi.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskRepository: TaskRepository,
) {
    @GetMapping
    fun getAllTasks(): List<Task> = taskRepository.findAll()

    @GetMapping("/{id}")
    fun getTask(
        @PathVariable id: Long,
    ): ResponseEntity<Task> {
        val task = taskRepository.findById(id)
        return if (task != null) {
            ResponseEntity.ok(task)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createTask(
        @RequestBody request: CreateTaskRequest,
    ): ResponseEntity<Task> {
        val task =
            Task(
                title = request.title,
                description = request.description,
            )
        val created = taskRepository.create(task)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @RequestBody request: UpdateTaskRequest,
    ): ResponseEntity<Task> {
        val updated = taskRepository.update(id, request.title, request.description, request.completed)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(
        @PathVariable id: Long,
    ): ResponseEntity<Void> =
        if (taskRepository.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
}
