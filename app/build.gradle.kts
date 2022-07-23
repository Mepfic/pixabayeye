plugins {
    kotlin("android")
    kotlin("kapt")
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.hiltPlugin)
}

android {
    namespace = "com.myapps.pixabayeye"
    compileSdk = AndroidSdk.compile
    buildToolsVersion = AndroidSdk.buildToolsVersion
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.myapps.pixabayeye.AppTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(project(":uiSearch"))
    implementation(project(":uiDetails"))

    androidTestImplementation(project(":domain"))
    androidTestImplementation(project(":testCommon"))
    androidTestImplementation(project(":uiCommon"))

    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.material)
    implementation(Libraries.hilt)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)

    androidTestImplementation(Libraries.pagingRuntimeKtx)
    androidTestImplementation(TestLibraries.androidTestCore)
    androidTestImplementation(TestLibraries.androidTestRunner)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.espressoContrib)
    androidTestImplementation(TestLibraries.espressoIntents)
    androidTestImplementation(TestLibraries.fragmentTesting)
    androidTestImplementation(TestLibraries.hiltTesting)
    androidTestImplementation(TestLibraries.hamcrest)

    debugImplementation(TestLibraries.fragmentTesting)
    androidTestUtil(TestLibraries.androidTestOrchestrator)

    kapt(Libraries.hiltCompiler)
    kaptAndroidTest(Libraries.hiltCompiler)
}

hilt {
    enableAggregatingTask = true
}
