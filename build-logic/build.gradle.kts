val jvmVersion: String = libs.versions.jvm.get()

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    compileOnly(libs.ktlint.gradlePlugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.javapoet)
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
            id = "library.base"
            implementationClass = "AndroidLibraryBaseConventionPlugin"
        }
        register("androidLibraryUi") {
            id = "library.ui"
            implementationClass = "AndroidLibraryUiConventionPlugin"
        }
    }
}
