import org.jetbrains.kotlin.gradle.plugin.KotlinTargetHierarchy.SourceSetTree.Companion.main

plugins {
    kotlin("jvm") version "1.9.0"
//    application
}

group = "jenm.examples"
version = "unspecified"

repositories {
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven/")
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:2.1.0")
    implementation("org.apache.kafka:kafka-streams:2.1.0")
    implementation("org.apache.kafka:connect-runtime:2.1.0")
    implementation("io.confluent:kafka-json-serializer:5.0.1")
    implementation("com.google.code.gson:gson:2.2.4")
    implementation("org.slf4j:slf4j-api:1.7.6")
    implementation("org.slf4j:slf4j-log4j12:1.7.6")
    implementation("org.testcontainers:kafka:1.19.7")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.register<JavaExec>("run") {
    mainClass.set("jenm.examples.kafka.MainKt")
    classpath = sourceSets.main.get().runtimeClasspath

    systemProperties.putAll(System.getProperties().map { it.key.toString() to it.value })
}
