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
//        maven { url = uri("https://nexus.pentaho.org/content/groups/omni/") }
        maven { url = uri("https://jitpack.io") }
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
