import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("dagger.hilt.android.plugin")
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

    buildFeatures {
        dataBinding = true
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
    implementation("androidx.core:core-ktx:_")
    implementation("androidx.appcompat:appcompat:_")
    implementation("com.google.android.material:material:_")
    implementation("androidx.constraintlayout:constraintlayout:_")
    implementation("com.squareup.retrofit2:retrofit:_")
    implementation("com.squareup.okhttp3:logging-interceptor:_")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:_")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:_")
    implementation("com.google.dagger:hilt-android:_")
    implementation("io.arrow-kt:arrow-core:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
    implementation("androidx.room:room-runtime:_")
    implementation("androidx.room:room-ktx:_")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    kapt("androidx.room:room-compiler:_")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:_")
    implementation("androidx.fragment:fragment-ktx:_")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:_")
    implementation("androidx.activity:activity-ktx:_")
    kapt("com.google.dagger:hilt-compiler:_")
    implementation("com.squareup.retrofit2:converter-gson:_")

    implementation("com.github.bumptech.glide:glide:_")
    kapt ("com.github.bumptech.glide:compiler:_")


    testImplementation ("junit:junit:_")
    androidTestImplementation ("androidx.test.ext:junit:_")
    androidTestImplementation ("androidx.test.espresso:espresso-core:_")
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
}