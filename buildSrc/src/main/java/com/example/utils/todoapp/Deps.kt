package com.example.utils.todoapp

object Deps {

    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    object Androidx {
        const val appcompat =
            "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val room =
            "androidx.room:room-runtime:${Versions.room}"
        const val material =
            "com.google.android.material:material:${Versions.material}"
    }

    object Ktx {
        const val core =
            "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragment =
            "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    }

    object DI {
        const val dagger =
            "com.google.dagger:dagger:${Versions.dagger}"
    }

    object LocalStore {
        const val room =
            "androidx.room:room-ktx:${Versions.room}"
        const val roomRuntime =
            "androidx.room:room-runtime:${Versions.room}"
        const val datastore =
            "androidx.datastore:datastore-preferences:${Versions.datastore}"
    }

    object Network {
        const val okhttp =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val retrofit =
            "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Kapt {
        const val room =
            "androidx.room:room-compiler:${Versions.room}"
        const val dagger =
            "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object Test {
        const val junit =
            "junit:junit:${Versions.junit}"
        const val junitTest =
            "androidx.test.ext:${Versions.junitTest}"
        const val mockk =
            "io.mockk:mockk:${Versions.mockk}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    }

}