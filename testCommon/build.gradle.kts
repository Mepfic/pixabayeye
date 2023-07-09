plugins {
    androidLibraryUiConvention
}

android.namespace = "com.myapps.pixabayeye.test.common"

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.test.junit)
    implementation(libs.kotlinx.coroutines.test)
}
