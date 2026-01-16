repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.javapoet)
}
