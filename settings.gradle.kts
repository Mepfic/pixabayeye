rootProject.name = "PixabayEye"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":app",
    ":data",
    ":domain",
    ":testCommon",
    ":uiCommon",
    ":uiDetails",
    ":uiSearch"
)
