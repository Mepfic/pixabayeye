val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = libs.findVersion("android-build-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("android-build-minSdk").get().requiredVersion.toInt()
        defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
        targetCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
    }
    kotlinOptions {
        jvmTarget = libs.findVersion("build-jvmTarget").get().requiredVersion
    }
}

dependencies {
    implementation(libs.findLibrary("androidx-paging-runtime-ktx").get())
    implementation(libs.findLibrary("hilt-android").get())
    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
    implementation(libs.findLibrary("timber").get())

    testImplementation(libs.findLibrary("androidx-test-core").get())
    testImplementation(libs.findLibrary("androidx-test-truth").get())
    testImplementation(libs.findLibrary("kotlin-reflect").get())
    testImplementation(libs.findLibrary("kotlin-test-junit").get())
    testImplementation(libs.findLibrary("kotlinx-coroutines-test").get())
    testImplementation(libs.findLibrary("mockk").get())
    testImplementation(libs.findLibrary("mockk-android").get())
    testImplementation(libs.findLibrary("turbine").get())

    androidTestImplementation(libs.findLibrary("androidx-test-espresso-core").get())
    androidTestImplementation(libs.findLibrary("kotlin-test-junit").get())

    kapt(libs.findLibrary("hilt-android-compiler").get())
}
