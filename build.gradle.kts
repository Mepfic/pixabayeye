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
        classpath(BuildPlugins.gradleHiltPlugin)
    }
}

plugins {
    id(BuildPlugins.detektPlugin) version (PluginVersions.detekt)
}

apply(from = "$rootDir/ci.gradle.kts")

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(from = "$rootDir/ktlint.gradle.kts")
    apply(plugin = BuildPlugins.detektPlugin)

    detekt {
        parallel = true
        ignoreFailures = false
    }
}

tasks.register<Delete>("clean") {
    delete("build")
}
