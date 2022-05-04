import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.ac.musicac"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    secrets {
        propertiesFileName = "secrets.properties"
    }

    signingConfigs {
        val properties = Properties().apply {
            load(File(secrets.propertiesFileName).reader())
        }
        getByName("debug") {
            keyAlias = (properties["debugKeyAlias"] ?: "") as String
            keyPassword = (properties["debugKeyPassword"] ?: "") as String
            storeFile = file((properties["debugKeyFileName"] ?: "") as String)
            storePassword = (properties["debugKeyPassword"] ?: "") as String
        }
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:_")
    implementation ("androidx.appcompat:appcompat:_")
    implementation ("com.google.android.material:material:_")
    implementation ("androidx.constraintlayout:constraintlayout:_")
    testImplementation ("junit:junit:_")
    androidTestImplementation ("androidx.test.ext:junit:_")
    androidTestImplementation ("androidx.test.espresso:espresso-core:_")
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
}