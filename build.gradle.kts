import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

subprojects {

    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        config = files("$rootDir/config/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "1.8"
        reports {
            xml.required.set(true)
            html.required.set(false)
            txt.required.set(false)
            sarif.required.set(false)
        }
    }

    afterEvaluate {
        // we don't want to run the detekt task in every build task to don't impact in
        // the building time. (check dependsOn build)
        tasks.named("check").configure {
            this.setDependsOn(this.dependsOn.filterNot {
                it is TaskProvider<*> && it.name == "detekt"
            })
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}