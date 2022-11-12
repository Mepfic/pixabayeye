plugins {
    androidLibraryBaseConvention
    id(BuildPlugins.hiltPlugin)
}

android {
    namespace = "com.myapps.pixabayeye.domain"
}

dependencies {
    implementation(project(":data"))
    testImplementation(project(":testCommon"))
}