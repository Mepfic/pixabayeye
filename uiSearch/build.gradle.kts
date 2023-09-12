plugins {
    androidLibraryUiConvention
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android.namespace = "com.myapps.pixabayeye.search"

dependencies {
    implementation(project(":domain"))
    implementation(project(":uiCommon"))

    testImplementation(project(":testCommon"))
    testImplementation(libs.androidx.paging.testing)
}
