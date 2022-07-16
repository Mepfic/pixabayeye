plugins {
    androidLibraryBaseConvention
}

android {
    namespace = "com.myapps.pixabayeye.data"
}

dependencies {
    implementation(Libraries.moshiKotlin)
    implementation(Libraries.moshiConverter)
    implementation(Libraries.retrofit)
    implementation(Libraries.loggingInterceptor)
}
