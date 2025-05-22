plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "org.dizzy.worldsat"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.dizzy.worldsat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation (libs.viewpager2) // Corrected dependency
    implementation (libs.glide)
    implementation(libs.firebase.database) // Corrected dependency
    testImplementation(libs.junit)

    androidTestImplementation(libs.ext.junit)

    implementation (libs.chipnavigationbar)
    androidTestImplementation(libs.espresso.core)
}