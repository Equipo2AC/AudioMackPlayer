plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.ac.audiomackplayer"
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