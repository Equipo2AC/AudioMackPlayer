import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Plugins.application)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.kotlinSerialization)
    id(Plugins.hiltAndroid)
    id(Plugins.secret)
    id(Plugins.safeArgs)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    secrets {
        propertiesFileName = AppConfig.propertiesFileName
    }

    buildFeatures {
        dataBinding = true
    }

    sourceSets {
        this.getByName("androidTest"){
            //Adds the given source directory to this set.
            this.java.srcDir("$projectDir/src/testShared/androidTest")
        }
        this.getByName("test"){
            this.java.srcDir("$projectDir/src/testShared/test")
        }
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
    implementation(Libs.Retrofit.retrofitCore)
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
    implementation(Libs.Glide.core)
    kapt (Libs.Glide.compiler)
    implementation(Libs.playServicesLocation)
    //TESTING
    testImplementation (Libs.JUnit.core)
    testImplementation (Libs.Mockito.kotlin)
    testImplementation (Libs.Mockito.inline)
    testImplementation (Libs.Kotlin.Coroutines.test)
    testImplementation (Libs.turbine)
    androidTestImplementation (Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation (Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation (Libs.AndroidX.Test.runner)
    androidTestImplementation (Libs.AndroidX.Test.rules)
    androidTestImplementation (Libs.Kotlin.Coroutines.test)
    androidTestImplementation (Libs.Hilt.test)
    androidTestImplementation (Libs.OkHttp3.mockWebServer)
    androidTestImplementation (Libs.AndroidX.Test.Espresso.intents)
    // debugImplementation(AndroidX.fragment.testing)
    // debugImplementation(Libs.AndroidX.Fragment.test)
    kaptAndroidTest (Libs.Hilt.compiler)
    //MODULES
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.usescases))
    testImplementation(project(Modules.testShared))
    androidTestImplementation(project(Modules.testShared))
}