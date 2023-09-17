// Top-level build file where you can add configuration options common to all sub-projects/modules.

@Suppress("GradlePluginVersion")
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPatch.androidGradlePlugin)
        classpath(ClassPatch.kotlinGradlePlugin)
        classpath(ClassPatch.detektGradlePlugin)
        classpath(ClassPatch.secretsGradlePlugin)
        classpath(ClassPatch.hiltAndroidGradlePlugin)
        classpath(ClassPatch.navigationSafeArgs)
    }
}

apply(from = "gradle-scripts/detekt.gradle")

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}