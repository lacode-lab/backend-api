package com.kurage.lab.backendapi.controller

import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class VersionInfo(
    val version: String,
    val branch: String,
    val tags: String?,
)

@RestController
class VersionController(
    private val buildProperties: BuildProperties,
    private val gitProperties: GitProperties,
) {
    @GetMapping("/api/admin/version")
    fun version() =
        VersionInfo(
            version = buildProperties.version,
            branch = gitProperties.branch,
            tags = gitProperties.get("tags"),
        )
}
