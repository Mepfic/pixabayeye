plugins {
    androidLibraryUiConvention
    id(BuildPlugins.navigationSafeArgs)
    id(BuildPlugins.hiltPlugin)
}

android {
    namespace = "com.myapps.pixabayeye.details"
}

dependencies {
    implementation(project(":uiCommon"))
    implementation(project(":domain"))

    testImplementation(project(":testCommon"))
}

//hilt {
//    enableExperimentalClasspathAggregation = true
//}
