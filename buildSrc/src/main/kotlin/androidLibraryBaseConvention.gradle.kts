plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compile
    buildToolsVersion = AndroidSdk.buildToolsVersion
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.timber)
    implementation(Libraries.hilt) {
        exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel")
        exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel-ktx")
    }
    implementation(Libraries.pagingRuntimeKtx)

    testImplementation(TestLibraries.kotlinReflect)
    testImplementation(TestLibraries.kotlinTest)
    testImplementation(TestLibraries.kotlinxCoroutinesTest)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.mockkAndroid)
    testImplementation(TestLibraries.flowTest)
    testImplementation(TestLibraries.androidTestCore)
    testImplementation(TestLibraries.androidTestExtTruth)

    kapt(Libraries.hiltCompiler)
}
