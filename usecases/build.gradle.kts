plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJVM)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    //MODULES
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    //LIBS
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.JavaX.inject)
    implementation(Libs.Arrow.core)
    //TEST
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)

}