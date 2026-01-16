import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(libs: VersionCatalog, alias: String) {
    add("implementation", libs.findLibrary(alias).get())
}

fun DependencyHandlerScope.testImplementation(libs: VersionCatalog, alias: String) {
    add("testImplementation", libs.findLibrary(alias).get())
}

fun DependencyHandlerScope.androidTestImplementation(libs: VersionCatalog, alias: String) {
    add("androidTestImplementation", libs.findLibrary(alias).get())
}