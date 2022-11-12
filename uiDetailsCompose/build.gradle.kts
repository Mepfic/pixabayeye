plugins {
    androidLibraryUiConvention
    id(BuildPlugins.navigationSafeArgs)
    id(BuildPlugins.hiltPlugin)
}

android {
    namespace = "com.myapps.pixabayeye.details"
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = PluginVersions.composeCompiler
    }
}

dependencies {
    val composeBom = platform(Libraries.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Libraries.composeUi)
    implementation(Libraries.composeUiToolingPreview)
    implementation(Libraries.composeFoundation)
    implementation(Libraries.composeFoundationLayout)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.composeMaterialIconCore)
    implementation(Libraries.composeAnimation)
    implementation(Libraries.composeRuntime)
    implementation(Libraries.composeConstraintLayout)
    implementation(Libraries.composeLifecycle)
    implementation(Libraries.composeLifecycleRuntime)
    implementation(Libraries.coilCompose)
    debugImplementation(Libraries.composeUiTooling)

    implementation(project(":domain"))
    implementation(project(":uiCommon"))

    testImplementation(project(":testCommon"))
}
//
//hilt {
//    enableExperimentalClasspathAggregation = true
//}