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

            pluginManager.apply("library.base")

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                implementation(libs, "android-material")
                implementation(libs, "androidx-activity-ktx")
                implementation(libs, "androidx-fragment-ktx")
                implementation(libs, "androidx-lifecycle-process")
                implementation(libs, "androidx-lifecycle-runtime-ktx")
                implementation(libs, "androidx-navigation-fragment-ktx")
                implementation(libs, "androidx-navigation-ui-ktx")
                implementation(libs, "androidx-swiperefreshlayout")
                implementation(libs, "coil")
                implementation(libs, "hilt-android")
                implementation(libs, "timber")
            }
        }
    }
}