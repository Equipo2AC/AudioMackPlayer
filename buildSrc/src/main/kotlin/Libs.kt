object Libs {

    const val playServicesLocation = "com.google.android.gms:play-services-location:_"

    object Kotlin {
        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:_"
        }

       const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:_"
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:_"
        const val appCompat = "androidx.appcompat:appcompat:_"
        const val recyclerView = "androidx.recyclerview:recyclerview:_"
        const val material = "com.google.android.material:material:_"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:_"

        object Activity {
            const val ktx = "androidx.activity:activity-ktx:_"
        }

        object Fragment {
            const val ktx = "androidx.fragment:fragment-ktx:_"
            const val test = "androidx.fragment:fragment-testing:_"
        }

        object Lifecycle {
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:_"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:_"
        }

        object Navigation {
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:_"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:_"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:_"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:_"
            const val ktx = "androidx.room:room-ktx:_"
            const val compiler = "androidx.room:room-compiler:_"
        }

        object Test {
            const val runner = "androidx.test:runner:_"
            const val rules = "androidx.test:rules:_"

            object Ext {
                const val junit = "androidx.test.ext:junit-ktx:_"
            }
            object Espresso{
                const val contrib = "androidx.test.espresso:espresso-contrib:_"
                const val intents = "androidx.test.espresso:espresso-intents:_"
                const val core = "androidx.test.espresso:espresso-core:_"
            }
        }

        // Libreria para test de livedata
        object Arch {
            const val coreTesting = "androidx.arch.core:core-testing:_"
        }
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:_"
        const val compiler = "com.github.bumptech.glide:compiler:_"
    }

    object OkHttp3 {
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:_"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:_"
    }

    object Retrofit {
        const val retrofitCore = "com.squareup.retrofit2:retrofit:_"
        const val converterGson = "com.squareup.retrofit2:converter-gson:_"
        const val kotlinSerializationConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:_"
    }

    object Arrow {
        const val core = "io.arrow-kt:arrow-core:_"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:_"
        const val compiler = "com.google.dagger:hilt-compiler:_"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:_"
        const val test = "com.google.dagger:hilt-android-testing:_"
    }

    object JUnit {
        const val core = "junit:junit:_"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:_"
        const val inline = "org.mockito:mockito-inline:_"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:_"
    }

    const val turbine = "app.cash.turbine:turbine:_"
}