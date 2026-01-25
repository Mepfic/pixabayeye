import org.jetbrains.kotlin.gradle.dsl.JvmTarget.Companion.fromTarget

val jvmVersion: String = libs.versions.jvm.get()

plugins {
    kotlin("android")
    id("com.android.application")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jvmVersion))
    }
}

android {
    namespace = "com.myapps.pixabayeye"
    compileSdk = libs.versions.android.build.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.build.minSdk.get().toInt()
        targetSdk = libs.versions.android.build.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.myapps.pixabayeye.AppTestRunner"
    }
    buildFeatures.compose = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        val javaVersion = JavaVersion.toVersion(libs.versions.jvm.get())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(fromTarget(libs.versions.jvm.get()))
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(project(":uiDetails"))
    implementation(project(":uiSearch"))
    implementation(project(":uiCommon"))
    implementation(project(":data"))

    androidTestImplementation(project(":data"))
    androidTestImplementation(project(":domain"))
    androidTestImplementation(project(":testCommon"))

    implementation(libs.androidx.material)
    implementation(libs.hilt.android)

    implementation(platform(libs.compose.core))
    implementation(libs.compose.foundation)
    implementation(libs.compose.navigation)
    implementation(libs.compose.navigation.runtime)
    implementation(libs.compose.runtime)
    implementation(libs.compose.preview)
    implementation(libs.compose.constraintLayout)
    implementation(libs.compose.navigation.hilt)
    implementation(libs.lifecycle.viewmodel.navigation)

    implementation(libs.compose.material)

    implementation(libs.coil)

    androidTestImplementation(libs.androidx.paging.runtime.ktx)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.hamcrest)
    androidTestImplementation(libs.hilt.android.testing)

    androidTestUtil(libs.androidx.test.orchestrator)

    ksp(libs.hilt.android.compiler)
    kspAndroidTest(libs.hilt.android.compiler)
}

hilt {
    enableAggregatingTask = true
}
