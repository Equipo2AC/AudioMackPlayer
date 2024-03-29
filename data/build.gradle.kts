plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJVM)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    //MODULES
    implementation(project(Modules.domain))
    testImplementation(project(Modules.testShared))
    //LIBS
    implementation(Libs.Arrow.core)
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.JavaX.inject)
    //TEST
    testImplementation(Libs.JUnit.core)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
}