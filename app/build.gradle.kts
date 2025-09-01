plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.appcomunicadosespol"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appcomunicadosespol"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Keep the aliases as they are the recommended way to manage dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Keep test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // These are redundant and should be removed
    // implementation(libs.constraintlayout.v214)
    // implementation(libs.appcompat.v161)
    // implementation(libs.material.v110)
}