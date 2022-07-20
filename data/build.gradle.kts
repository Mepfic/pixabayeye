import java.util.Properties

plugins {
    androidLibraryBaseConvention
}

android {
    namespace = "com.myapps.pixabayeye.data"

    defaultConfig {
        file("$rootDir/buildSrc/src/keys/apikeys.properties").let { file ->
            if (file.exists()) {
                val appProperties = Properties()
                appProperties.load(file.inputStream())
                val key = appProperties.getProperty("API_KEY").orEmpty()
                buildConfigField("String", "API_KEY", key)
            }
        }
    }
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
