plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "uk.cmdrnorthpaw"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://jitpack.io/")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")

    implementation("org.eclipse.jetty:jetty-server:11.0.0.beta2")

    // Ktor artifacts
    implementation("io.ktor:ktor-client-core:1.4.1")
    implementation("io.ktor:ktor-client-jetty:1.4.1")

    implementation("com.beust:klaxon:5.0.1")
}
