plugins {
    id("androidLibraryBaseConvention")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.timber)
    implementation(Libraries.lifecycleCommon)
    implementation(Libraries.lifecycleLivedataKtx)
    implementation(Libraries.lifecycleProcess)
    implementation(Libraries.lifecycleRuntimeKtx)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.material)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationFragment)

    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // unit test
    testImplementation(TestLibraries.kotlinReflect)
    testImplementation(TestLibraries.kotlinTest)
    testImplementation(TestLibraries.kotlinxCoroutinesTest)
    testImplementation(TestLibraries.flowTest)
}
