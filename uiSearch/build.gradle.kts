plugins {
    alias(libs.plugins.android.library.ui)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android.namespace = "com.myapps.pixabayeye.search"

dependencies {
    implementation(project(":domain"))
    implementation(project(":uiCommon"))
    implementation(project(":testCommon"))

    testImplementation(project(":testCommon"))

    ksp(libs.hilt.android.compiler)
}
