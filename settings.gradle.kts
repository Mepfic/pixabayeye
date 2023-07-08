// pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        google()
//        mavenCentral()
//    }
// }
// dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        google()
//        mavenCentral()
//    }
// }
rootProject.name = "PixabayEye"
include(
    ":app",
    ":data",
    ":domain",
    ":uiCommon",
    ":uiSearch",
    ":uiDetails",
    ":testCommon"
)
