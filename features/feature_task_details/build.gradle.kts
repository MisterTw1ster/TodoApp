import com.example.utils.todoapp.Base
import com.example.utils.todoapp.Deps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Base.currentSDK

    defaultConfig {
        minSdk = Base.minSDK
        targetSdk = Base.currentSDK
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    
    implementation(project(":core_task_repository"))
    implementation(project(":core_domain"))
    implementation(project(":core"))
    implementation(project(":feature_views"))

    implementation(Deps.Ktx.core)
    implementation(Deps.Ktx.fragment)
    implementation(Deps.Ktx.viewModel)

    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.material)

    implementation(Deps.DI.dagger)
    kapt(Deps.Kapt.dagger)

}
