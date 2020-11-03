plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.spongepowered.plugin") version "0.9.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
    implementation(project(":mercury-common"))
    compileOnly("org.spongepowered:spongeapi:7.3.0")
    annotationProcessor("org.spongepowered:spongeapi:7.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")
    implementation(kotlin("stdlib-jdk8"))
}

sponge {
    plugin.id = "mercury-sponge"
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.shadowJar {
    dependencies {
        include(project(":mercury-common"))
    }
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
