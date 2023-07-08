import org.apache.tools.ant.taskdefs.condition.Os

tasks.register("codeFormatAndCheck") {
    doLast {
        gradlew(
            "clean",
            "ktlintFormat",
            "lintFix",
            "ktlintCheck",
            "lint",
            "detekt"
        )
    }
}

tasks.register("allTest") {
    doLast {
        gradlew(
            "clean",
            "testDebugUnitTest",
            "connectedAndroidTest"
        )
    }
}

fun gradlew(vararg tasks: String) {
    exec {
        executable = File(
            project.rootDir,
            if (Os.isFamily(Os.FAMILY_WINDOWS)) "gradlew.bat" else "gradlew"
        )
            .also { it.setExecutable(true) }
            .absolutePath

        args = mutableListOf<String>().apply {
            addAll(tasks)
            add("--stacktrace")
        }
        println("commandLine: ${this.commandLine}")
    }.apply { println("ExecResult: $this") }
}
