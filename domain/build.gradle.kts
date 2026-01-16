plugins {
    androidLibraryBaseConvention
    alias(libs.plugins.ksp)
}

android.namespace = "com.myapps.pixabayeye.domain"

dependencies {
    implementation(project(":data"))
    testImplementation(project(":testCommon"))
    ksp(libs.hilt.android.compiler)
}
