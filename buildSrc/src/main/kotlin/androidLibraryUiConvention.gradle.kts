plugins {
    id("androidLibraryBaseConvention")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        compose = true
    }

    flavorDimensions.apply {
        add(UiDimension.DIMENSION)
    }

    productFlavors {
        UiDimension.addProductFlavors(this)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = PluginVersions.composeCompiler
    }
}

dependencies {
    implementation(Libraries.timber)
    implementation(Libraries.lifecycleViewModel)
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
}
