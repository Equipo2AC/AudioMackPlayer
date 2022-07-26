plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJVM)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
    implementation("javax.inject:javax.inject:_")
    implementation("io.arrow-kt:arrow-core:_")

}