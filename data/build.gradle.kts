import java.util.Properties

plugins {
    androidLibraryBaseConvention
}

android {
    namespace = "com.myapps.pixabayeye.data"

    buildFeatures.buildConfig = true

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
    androidTestImplementation(project(":testCommon"))

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.kotlin.reflect)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.room.runtime)

    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.room.testing)

    kapt(libs.room.compiler)
}
