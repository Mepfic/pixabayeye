plugins {
    id("library.base")
    alias(libs.plugins.ksp)
}

android.namespace = "com.myapps.pixabayeye.domain"

dependencies {
    testImplementation(project(":testCommon"))
    ksp(libs.hilt.android.compiler)
}
