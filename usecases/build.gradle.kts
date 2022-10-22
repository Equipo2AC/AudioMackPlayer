plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJVM)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    //MODULES
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    testImplementation(project(Modules.testShared))
    //LIBS
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.JavaX.inject)
    implementation(Libs.Arrow.core)
    //TEST
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)

}