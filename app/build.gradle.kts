plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
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
        sourceCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.build.jvmTarget.get()
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    kapt {
        arguments {
            arg("dagger.hilt.shareTestComponents", "true")
        }
    }
}

dependencies {
    implementation(project(":uiDetails"))
    implementation(project(":uiSearch"))

    androidTestImplementation(project(":domain"))
    androidTestImplementation(project(":testCommon"))
    androidTestImplementation(project(":uiCommon"))

    implementation(libs.android.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.hilt.android)

    androidTestImplementation(libs.androidx.fragment.testing)
    androidTestImplementation(libs.androidx.paging.runtime.ktx)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.intents)
    androidTestImplementation(libs.hamcrest)
    androidTestImplementation(libs.hilt.android.testing)

    debugImplementation(libs.androidx.fragment.testing)
    androidTestUtil(libs.androidx.test.orchestrator)

    kapt(libs.hilt.android.compiler)
    kaptAndroidTest(libs.hilt.android.compiler)
}

hilt {
    enableAggregatingTask = true
}
