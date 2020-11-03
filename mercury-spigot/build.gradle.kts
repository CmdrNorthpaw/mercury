plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":mercury-common"))
    compileOnly("org.spigotmc:spigot-api:1.16.3-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.processResources {
    expand("version" to version)
}

tasks.shadowJar {
    dependencies {
        include(project(":mercury-common"))
    }
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
