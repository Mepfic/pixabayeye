import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

/**
 * Common tasks plugin
 */
class TasksConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            registerKtLintTasks()
            registerCodeQualityTasks()
            registerTestTasks()
        }
    }

    private fun Project.registerKtLintTasks() {
        pluginManager.apply("org.jlleitschuh.gradle.ktlint")

        extensions.configure<KtlintExtension>("ktlint") {
            version.set("1.1.0")
            android.set(true)

            filter {
                exclude("**/build/**")
                exclude("**/buildSrc/**")
                exclude("**/.gradle/**")
            }

            reporters {
                reporter(ReporterType.PLAIN)
                reporter(ReporterType.CHECKSTYLE)
                reporter(ReporterType.JSON)
            }

            outputToConsole.set(true)
            outputColorName.set("RED")
            ignoreFailures.set(true)

            // EditorConfig support
            enableExperimentalRules.set(false)
        }

    }

    private fun Project.registerCodeQualityTasks() {
        tasks.register("codeFormatAndCheck") {
            group = DEVELOP_GROUP
            description = "Formats and checks code quality"

            dependsOn(
                "clean",
                "ktlintFormat",
                "lintFix",
                "ktlintCheck",
                "lint",
                "detekt"
            )

            // setup execution order
            tasks.findByName("ktlintFormat")?.mustRunAfter("clean")
            tasks.findByName("lintFix")?.mustRunAfter("ktlintFormat")
            tasks.findByName("ktlintCheck")?.mustRunAfter("lintFix")
            tasks.findByName("lint")?.mustRunAfter("ktlintCheck")
            tasks.findByName("detekt")?.mustRunAfter("lint")
        }
    }

    private fun Project.registerTestTasks() {
        // All tests (unit + instrumentation)
        tasks.register("allTest") {
            group = DEVELOP_GROUP
            description = "Runs all unit and instrumentation tests"

            dependsOn(
                "clean",
                "testDebugUnitTest",
                "connectedDebugAndroidTest"
            )

            tasks.findByName("testDebugUnitTest")?.mustRunAfter("clean")
            tasks.findByName("connectedDebugAndroidTest")?.mustRunAfter("testDebugUnitTest")
        }

    }

    companion object {
        const val DEVELOP_GROUP = "Develop \uD83D\uDCBB"
    }
}