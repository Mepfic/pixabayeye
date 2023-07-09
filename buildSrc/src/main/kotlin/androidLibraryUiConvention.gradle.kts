val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("androidLibraryBaseConvention")
}

android.buildFeatures.viewBinding = true

dependencies {
    implementation(libs.findLibrary("android-material").get())
    implementation(libs.findLibrary("androidx-activity-ktx").get())
    implementation(libs.findLibrary("androidx-fragment-ktx").get())
    implementation(libs.findLibrary("androidx-lifecycle-process").get())
    implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
    implementation(libs.findLibrary("androidx-navigation-fragment-ktx").get())
    implementation(libs.findLibrary("androidx-navigation-ui-ktx").get())
    implementation(libs.findLibrary("androidx-swiperefreshlayout").get())
    implementation(libs.findLibrary("coil").get())
    implementation(libs.findLibrary("hilt-android").get())
    implementation(libs.findLibrary("timber").get())

    kapt(libs.findLibrary("hilt-android-compiler").get())
}
