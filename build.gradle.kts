import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
    }
}

intellijPlatform {
    projectName = "ClasspathIssue"
    pluginConfiguration {
        id = "org.example.classpath.issue"
        name = "Classpath Issue"
        version = project.version.toString()
    }

  instrumentCode = false
}


dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2025.1", useInstaller = false)
        jetbrainsRuntime()

        testFramework(TestFrameworkType.Bundled)
    }
    testImplementation(kotlin("test"))
    testImplementation("org.opentest4j:opentest4j:1.3.0")
    testImplementation("io.github.classgraph:classgraph:4.8.179")
}

tasks {
    test {
        jvmArgumentProviders += CommandLineArgumentProvider {
            // Manually set `MultiRoutingFileSystemProvider` as default filesystem provider to reproduce the issue on any OS
            listOf(
                "-Djava.nio.file.spi.DefaultFileSystemProvider=com.intellij.platform.core.nio.fs.MultiRoutingFileSystemProvider"
            )
        }
    }
}
