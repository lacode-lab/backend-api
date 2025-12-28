package com.kurage.lab.backendapi.repository

import com.kurage.lab.backendapi.model.Task
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Statement
import java.sql.Timestamp

@Repository
class TaskRepository(
    private val jdbcTemplate: JdbcTemplate,
) {
    private val rowMapper =
        RowMapper { rs, _ ->
            Task(
                id = rs.getLong("id"),
                title = rs.getString("title"),
                description = rs.getString("description"),
                completed = rs.getBoolean("completed"),
                createdAt = rs.getTimestamp("created_at").toLocalDateTime(),
                updatedAt = rs.getTimestamp("updated_at").toLocalDateTime(),
            )
        }

    fun findAll(): List<Task> = jdbcTemplate.query("SELECT * FROM tasks ORDER BY created_at DESC", rowMapper)

    fun findById(id: Long): Task? = jdbcTemplate.query("SELECT * FROM tasks WHERE id = ?", rowMapper, id).firstOrNull()

    fun create(task: Task): Task {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection ->
            val ps =
                connection.prepareStatement(
                    "INSERT INTO tasks (title, description, completed) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS,
                )
            ps.setString(1, task.title)
            ps.setString(2, task.description)
            ps.setBoolean(3, task.completed)
            ps
        }, keyHolder)

        val generatedId = (keyHolder.keys?.get("id") as Number).toLong()
        return findById(generatedId)!!
    }

    fun update(
        id: Long,
        title: String?,
        description: String?,
        completed: Boolean?,
    ): Task? {
        val existing = findById(id) ?: return null

        val newTitle = title ?: existing.title
        val newDescription = description ?: existing.description
        val newCompleted = completed ?: existing.completed

        jdbcTemplate.update(
            "UPDATE tasks SET title = ?, description = ?, completed = ?, updated_at = ? WHERE id = ?",
            newTitle,
            newDescription,
            newCompleted,
            Timestamp.valueOf(java.time.LocalDateTime.now()),
            id,
        )
        return findById(id)
    }

    fun delete(id: Long): Boolean {
        val rowsAffected = jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id)
        return rowsAffected > 0
    }
}
