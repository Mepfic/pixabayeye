plugins {
    androidLibraryUiConvention
}

android {
    namespace = "com.myapps.pixabayeye.test.common"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(TestLibraries.kotlinReflect)
    implementation(TestLibraries.kotlinTest)
    implementation(TestLibraries.kotlinxCoroutinesTest)
}
