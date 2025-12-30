plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.gorylenko.gradle-git-properties") version "2.5.4"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.kurage.lab"
version = "0.0.1-SNAPSHOT"
// リリースの度にversionを上げるか、CIで自動付与するパターンが多い
// version = System.getenv("GIT_TAG") ?: "0.0.1-SNAPSHOT"
description = "backend-api"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

springBoot {
    buildInfo()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
gitProperties {
    keys =
        listOf(
            "git.branch",
            "git.commit.id",
            "git.commit.id.abbrev",
            "git.commit.time",
            "git.build.time",
            "git.tags",
            "git.dirty",
        )
    dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
    dateFormatTimeZone = "UTC"
//    customProperty("git.build.time.epoch", object : groovy.lang.Closure<Long>(this) {
//        override fun call(vararg args: Any?): Long = System.currentTimeMillis()
//    })
//    customProperty("git.commit.time.epoch", object : groovy.lang.Closure<Long>(this) {
//        override fun call(vararg args: Any?): Long {
//            val grgit = args.firstOrNull()
//            return (grgit as? gradlegitproperties.org.ajoberstar.grgit.Grgit)?.head()?.dateTime?.toInstant()?.toEpochMilli() ?: 0L
//        }
//    })
}

tasks.processResources {
    dependsOn("generateGitProperties", "bootBuildInfo")
}
spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint("1.5.0")
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint("1.5.0")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
