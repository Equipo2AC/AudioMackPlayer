import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.

@Suppress("GradlePluginVersion")
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:_")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:_")
        classpath("com.google.dagger:hilt-android-gradle-plugin:_")
    }
}

apply(from = "gradle-scripts/detekt.gradle")

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}