repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(buildSrcLibs.android.gradle.plugin)
    implementation(buildSrcLibs.javapoet)
    implementation(buildSrcLibs.kotlin.gradle.plugin)
}
