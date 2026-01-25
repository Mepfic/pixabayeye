import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryBaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("android-build-compileSdk").get().requiredVersion.toInt()

                defaultConfig {
                    minSdk = libs.findVersion("android-build-minSdk").get().requiredVersion.toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                val javaVersionString = libs.findVersion("jvm").get().requiredVersion
                val javaVersion = JavaVersion.toVersion(javaVersionString)

                compileOptions {
                    sourceCompatibility = javaVersion
                    targetCompatibility = javaVersion
                }

                extensions.configure<KotlinAndroidProjectExtension> {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.fromTarget(javaVersionString))
                    }
                }
            }

            dependencies {
                implementation(libs, "androidx-paging-runtime-ktx")
                implementation(libs, "hilt-android")
                implementation(libs, "kotlinx-coroutines-core")
                implementation(libs, "timber")

                testImplementation(libs, "androidx-test-core")
                testImplementation(libs, "androidx-test-truth")
                testImplementation(libs, "kotlin-reflect")
                testImplementation(libs, "kotlin-test-junit")
                testImplementation(libs, "kotlinx-coroutines-test")
                testImplementation(libs, "mockk")
                testImplementation(libs, "mockk-android")
                testImplementation(libs, "turbine")

                androidTestImplementation(libs, "androidx-test-runner")
                androidTestImplementation(libs, "androidx-test-core")
                androidTestImplementation(libs, "kotlin-test-junit")
            }
        }
    }
}
