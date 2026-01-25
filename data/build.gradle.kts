import java.util.Properties

plugins {
    alias(libs.plugins.android.library.base)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.myapps.pixabayeye.data"

    buildFeatures.buildConfig = true

    defaultConfig {
        file("$rootDir/build-logic/src/keys/apikeys.properties").let { file ->
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
    implementation(project(":domain"))
    androidTestImplementation(project(":testCommon"))

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

    ksp(libs.room.compiler)
    ksp(libs.hilt.android.compiler)
}
