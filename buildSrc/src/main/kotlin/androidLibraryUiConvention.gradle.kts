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
    implementation(Libraries.swipeRefreshLayout)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.coil)
    implementation(Libraries.hilt)

    kapt(Libraries.hiltCompiler)
}
