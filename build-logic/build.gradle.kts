val jvmVersion: String = libs.versions.jvm.get()

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.ktlint.gradlePlugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jvmVersion))
    }
}

gradlePlugin {
    plugins {
        register("CustomTasksPlugin") {
            id = "tasksPlugin"
            implementationClass = "TasksConventionPlugin"
        }
        register("androidLibraryBase") {
            id = libs.plugins.android.library.base.get().pluginId
            implementationClass = "AndroidLibraryBaseConventionPlugin"
        }
        register("androidLibraryUi") {
            id = libs.plugins.android.library.ui.get().pluginId
            implementationClass = "AndroidLibraryUiConventionPlugin"
        }
        register("kmpLibraryBase") {
            id = libs.plugins.kmp.library.base.get().pluginId
            implementationClass = "KmpLibraryBaseConventionPlugin"
        }
    }
}
