plugins {
    androidLibraryUiConvention
    id(BuildPlugins.navigationSafeArgs)
    id(BuildPlugins.hiltPlugin)
}

android {
    namespace = "com.myapps.pixabayeye.search"
}

dependencies {
    implementation(project(":uiCommon"))
    implementation(project(":domain"))

    testImplementation(project(":testCommon"))
}
//
//hilt {
//    enableExperimentalClasspathAggregation = true
//}
