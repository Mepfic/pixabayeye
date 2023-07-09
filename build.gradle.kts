buildscript {
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.androidx.navigation.safeArgsGradlePlugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.javapoet)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.serialization)
    }
}

plugins {
    alias(libs.plugins.detekt)
}

apply(from = "$rootDir/ci.gradle.kts")

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(from = "$rootDir/ktlint.gradle")

    detekt {
        parallel = true
        ignoreFailures = false
    }
}

tasks.register<Delete>("clean") {
    delete("build")
}
