package com.kurage.lab.backendapi.model

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
)

data class CreateTaskRequest(
    val title: String,
    val description: String? = null,
)

data class UpdateTaskRequest(
    val title: String? = null,
    val description: String? = null,
    val completed: Boolean? = null,
)
