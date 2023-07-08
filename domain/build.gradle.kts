plugins {
    androidLibraryBaseConvention
}

android {
    namespace = "com.myapps.pixabayeye.domain"
}

dependencies {
    implementation(project(":data"))
    testImplementation(project(":testCommon"))
}
