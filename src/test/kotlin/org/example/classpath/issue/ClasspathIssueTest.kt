package org.example.classpath.issue

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.lang.UrlClassLoader
import io.github.classgraph.ClassGraph
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ClasspathIssueTest : BasePlatformTestCase() {

    @Test
    fun `test classpath introspection`() {
        val scanResult = ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages("org.example.classpath.issue")
            .scan()
        println("Found classes: ${scanResult.allClasses.size}")
    }

    @Test
    fun `test simplified example`() {
        val firstFileInClasspath = (javaClass.classLoader as? UrlClassLoader)?.files?.firstOrNull()
        println(firstFileInClasspath?.toFile())
    }
}
