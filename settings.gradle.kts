rootProject.name = "PixabayEye"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
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
