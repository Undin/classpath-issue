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
    }
}