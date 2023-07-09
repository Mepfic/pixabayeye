plugins {
    androidLibraryUiConvention
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android.namespace = "com.myapps.pixabayeye.details"

dependencies {
    implementation(project(":domain"))
    implementation(project(":uiCommon"))

    testImplementation(project(":testCommon"))
}
