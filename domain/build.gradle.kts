plugins {
    alias(libs.plugins.kmp.library.base)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.paging.compose)
            }
        }
    }
}

