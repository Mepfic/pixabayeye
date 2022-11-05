import java.util.Properties

private val versionsProperties = Properties().apply {
    Versions::class.java.classLoader?.getResourceAsStream("build_versions.properties")
        .use { load(it) }
}

private object Versions {
    val kotlin = versionsProperties["version.kotlin"].toString()
    const val kotlinxCoroutines = "1.6.3"
    const val kotlinxDateTime = "0.3.2"
    const val kotlinxSerialization = "1.3.2"
    const val activity = "1.6.1"
    const val coil = "2.2.2"
    const val coreKtx = "1.7.0"
    const val flowTestVersion = "0.8.0"
    const val fragment = "1.5.4"
    const val lifecycle = "2.5.1"
    const val material = "1.7.0"
    const val mockk = "1.12.4"
    const val retrofit = "2.9.0"
    const val moshiConverter = "2.9.0"
    const val moshi = "1.14.0"
    const val loggingInterceptor = "4.10.0"
    const val navigation = "2.5.3"
    const val paging = "3.1.1"
    const val swipeRefreshLayout = "1.1.0"
    const val hilt = "2.44"
    const val timber = "5.0.1"
    const val room = "2.4.3"
    const val androidTest = "1.4.0"
    const val androidTestRunner = "1.4.0"
    const val androidTestTruth = "1.4.0"
    const val espresso = "3.4.0"
    const val hamcrest = "2.2"
    const val androidTestOrchestrator = "1.4.1"
}

object PluginVersions {
    val androidGradlePlugin = versionsProperties["version.androidGradlePlugin"].toString()
    const val ktlint = "0.45.2"
    const val detekt = "1.20.0"
    const val hiltPlugin = Versions.hilt
}

object AndroidSdk {
    const val min = 23
    const val compile = 33
    const val target = 32
    const val buildToolsVersion = "33.0.0"
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
    val kotlinGradlePlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val kotlinSerializationPlugin by lazy {
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }
    val gradleHiltPlugin by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }

}

object Libraries {
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activity}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment}" }
    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
    val ktxCore by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val lifecycleLivedataKtx by lazy {
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }
    val lifecycleProcess by lazy { "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}" }
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
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
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomPaging by lazy { "androidx.room:room-paging:${Versions.room}" }
}

object TestLibraries {
    val androidTestCore by lazy { "androidx.test:core-ktx:${Versions.androidTest}" }
    val androidTestRunner by lazy { "androidx.test:runner:${Versions.androidTestRunner}" }
    val androidTestExtTruth by lazy { "androidx.test.ext:truth:${Versions.androidTestTruth}" }
    val androidTestOrchestrator by lazy { "androidx.test:orchestrator:${Versions.androidTestOrchestrator}" }
    val hamcrest by lazy { "org.hamcrest:hamcrest:${Versions.hamcrest}" }

    val fragmentTesting by lazy { "androidx.fragment:fragment-testing:${Versions.fragment}" }

    val kotlinReflect by lazy { "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}" }
    val kotlinTest by lazy { "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}" }
    val kotlinxCoroutinesTest by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
    }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val mockkAndroid by lazy { "io.mockk:mockk-android:${Versions.mockk}" }

    val flowTest by lazy { "app.cash.turbine:turbine:${Versions.flowTestVersion}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val espressoContrib by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espresso}" }
    val espressoIntents by lazy { "androidx.test.espresso:espresso-intents:${Versions.espresso}" }

    val roomTesting by lazy { "androidx.room:room-testing:${Versions.room}" }
    val hiltTesting by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
}
