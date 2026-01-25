import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply("android.library.base")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    buildFeatures.compose = true
                }
            }

            dependencies {
                implementation(libs, "coil")
                implementation(libs, "coil-compose")
                implementation(libs, "hilt-android")
                implementation(libs, "timber")

                val compose = platform(libs.findLibrary("compose-core").get())
                add("implementation", compose)

                implementation(libs, "compose-foundation")
                implementation(libs, "compose-navigation")
                implementation(libs, "compose-navigation-runtime")
                implementation(libs, "compose-runtime")
                implementation(libs, "compose-preview")
                implementation(libs, "compose-constraintLayout")
                implementation(libs, "compose-navigation-hilt")
                implementation(libs, "lifecycle-viewmodel-navigation")
                implementation(libs, "compose-material")
                implementation(libs, "paging-compose")

                testImplementation(libs, "androidx-paging-testing")
            }
        }
    }
}