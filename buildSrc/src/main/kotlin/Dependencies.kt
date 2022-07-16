private object Versions {
    const val kotlin = "1.6.10"
    const val kotlinxCoroutines = "1.6.3"
    const val kotlinxDateTime = "0.3.2"
    const val kotlinxSerialization = "1.3.2"
    const val activity = "1.4.0"
    const val coil = "2.0.0"
    const val coreKtx = "1.7.0"
    const val flowTestVersion = "0.8.0"
    const val fragment = "1.4.1"
    const val junit = "4.13.2"
    const val lifecycle = "2.4.1"
    const val material = "1.6.1"
    const val mockk = "1.12.4"
    const val retrofit = "2.9.0"
    const val moshiConverter = "2.9.0"
    const val moshi = "1.12.0"
    const val loggingInterceptor = "4.10.0"
    const val navigation = "2.4.2"
    const val paging = "3.0.1"
    const val swipeRefreshLayout = "1.1.0"
    const val hilt = "2.38.1"
    const val timber = "5.0.1"
}

object PluginVersions {
    const val kotlin = Versions.kotlin
    const val androidGradlePlugin = "7.1.3"
    const val ktlint = "0.45.2"
    const val detekt = "1.20.0"
    const val hiltPlugin = Versions.hilt
}

object AndroidSdk {
    const val min = 23
    const val compile = 32
    const val target = 31
    const val buildToolsVersion = "32.0.0"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
    const val kotlinPluginParcelize = "plugin.parcelize"
    const val serializationPlugin = "plugin.serialization"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val hiltPlugin = "dagger.hilt.android.plugin"

    val navigationSafeArgsGradlePlugin by lazy {
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    }
    val androidGradlePlugin by lazy { "com.android.tools.build:gradle:${PluginVersions.androidGradlePlugin}" }
    val kotlinGradlePlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}" }
    val kotlinSerializationPlugin by lazy {
        "org.jetbrains.kotlin:kotlin-serialization:${PluginVersions.kotlin}"
    }
    val gradleHiltPlugin by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }

}

object Libraries {
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activity}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment}" }
    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
    val ktxCore by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val lifecycleCommon by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" }
    val lifecycleLivedataKtx by lazy {
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }
    val lifecycleProcess by lazy { "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}" }
    val lifecycleRuntimeKtx by lazy {
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }
    val navigationFragment by lazy {
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val pagingRuntimeKtx by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
    val kotlinReflect by lazy { "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}" }
    val kotlinStdLib by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}" }
    val kotlinxCoroutines by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    }
    val kotlinxDateTime by lazy { "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}" }
    val kotlinxSerializationJson by lazy {
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val swipeRefreshLayout by lazy {
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    }

}

object TestLibraries {
    //    val androidTestCore by lazy {  "androidx.test:core-ktx:${Versions.androidTest}"}
    val kotlinReflect by lazy { "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}" }
    val kotlinTest by lazy { "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}" }
    val kotlinxCoroutinesTest by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
    }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val flowTest by lazy { "app.cash.turbine:turbine:${Versions.flowTestVersion}" }

    //    val espressoContrib  by lazy {  "androidx.test.espresso:espresso-contrib:${Versions.espresso}"}
//    val espressoCore by lazy {  "androidx.test.espresso:espresso-core:${Versions.espresso}"}
//    val espressoIntents by lazy {  "androidx.test.espresso:espresso-intents:${Versions.espresso}"}
    val fragmentTesting by lazy { "androidx.fragment:fragment-testing:${Versions.fragment}" }
}
