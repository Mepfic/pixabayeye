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

    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomPaging)
    kapt(Libraries.roomCompiler)
}
