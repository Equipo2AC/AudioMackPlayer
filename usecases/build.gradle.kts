plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJVM)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.JavaX.inject)
    implementation(Libs.Arrow.core)

}