import org.jetbrains.kotlin.konan.properties.loadProperties

repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

val versions = loadProperties("$projectDir/src/main/resources/build_versions.properties")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions["version.kotlin"]}")
    implementation("com.android.tools.build:gradle:${versions["version.androidGradlePlugin"]}")
}

configurations.all {
    resolutionStrategy.eachDependency {
        when (requested.name) {
            "javapoet" -> useVersion("1.13.0") // workaround for hilt issue after v2.40.1
        }
    }
}