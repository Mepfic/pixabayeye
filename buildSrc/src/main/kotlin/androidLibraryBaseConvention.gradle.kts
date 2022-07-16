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

    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // unit test
    testImplementation(TestLibraries.kotlinReflect)
    testImplementation(TestLibraries.kotlinTest)
    testImplementation(TestLibraries.kotlinxCoroutinesTest)
    testImplementation(TestLibraries.flowTest)
}
