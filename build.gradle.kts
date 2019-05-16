import org.jetbrains.intellij.IntelliJPluginExtension
import org.jetbrains.intellij.tasks.PatchPluginXmlTask

buildscript {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("gradle.plugin.org.jetbrains.intellij.plugins:gradle-intellij-plugin:0.3.7")
    }
}

description = "Idea Plugin Sample"

tasks.existing(Wrapper::class) {
    gradleVersion = "4.10.2"
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("java")
    id("org.jetbrains.intellij") version ("0.3.7")
    id("maven-publish")
}

tasks.withType(type = JavaCompile::class) {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.4")

    compile("org.freemarker:freemarker:2.3.28")

    compile("commons-io:commons-io:2.6")
    compile("com.squareup.retrofit2:retrofit:2.4.0")
    compile("com.squareup.retrofit2:converter-jackson:2.4.0")
    compile("com.squareup.okhttp3:logging-interceptor:3.12.1")
}

configure<IntelliJPluginExtension> {
    version = "2018.3.2"
}

tasks.withType(PatchPluginXmlTask::class) {
    changeNotes("Add change notes here.<br><em>most HTML tags may be used</em>")
}

val fatJar = task("fatJar", type = Jar::class) {
    from(configurations.compile.map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks["jar"] as CopySpec)
}

tasks {
    "buildPlugin" {
        dependsOn(fatJar)
    }
}
