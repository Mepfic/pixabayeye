plugins {
    id("library.ui")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
}

android.namespace = "com.myapps.pixabayeye.details"

dependencies {
    implementation(project(":domain"))
    implementation(project(":uiCommon"))

    testImplementation(project(":testCommon"))
    ksp(libs.hilt.android.compiler)
}
