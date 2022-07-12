buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.kotlinSerializationPlugin)
        classpath(BuildPlugins.navigationSafeArgsGradlePlugin)
        classpath(BuildPlugins.hiltPlugin)
    }
}

plugins {
    id(BuildPlugins.detektPlugin) version (PluginVersions.detekt)
}


allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = BuildPlugins.detektPlugin)

    detekt {
        parallel = true
        ignoreFailures = false
    }

}

tasks.register<Delete>("clean") {
    delete("build")
}

//println("Gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
//System.getProperties().forEach { key, value -> println("System.Property: $key=$value") }
//System.getenv().forEach { (key, value) -> println("System.env: $key=$value") }