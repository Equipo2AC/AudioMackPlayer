import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Plugins.application)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.kotlinSerialization)
    id(Plugins.hiltAndroid)
    id(Plugins.secret)
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
    //LIBS
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.OkHttp3.loginInterceptor)
    implementation(Libs.Retrofit.kotlinSerializationConverter)
    implementation(Libs.Kotlin.serializationJson)
    implementation(Libs.Hilt.android)
    implementation(Libs.Arrow.core)
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)
    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Fragment.ktx)
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)
    implementation(Libs.AndroidX.Activity.ktx)
    kapt(Libs.Hilt.compiler)
    implementation(Libs.Retrofit.converterGson)
    implementation(Libs.Glide.glide)
    kapt (Libs.Glide.compiler)
    implementation(Libs.playServicesLocation)
    //TESTING
    testImplementation (Libs.JUnit.junit)
    androidTestImplementation (Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation (Libs.AndroidX.Test.Espresso.core)
    //MODULES
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.usescases))
}