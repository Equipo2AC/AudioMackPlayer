pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    id("de.fayard.refreshVersions") version("0.60.2")
////                            # available:"0.60.3")
}
rootProject.name = "MusicAC"
include (":app",":data",":domain",":usecases",":testShared")
