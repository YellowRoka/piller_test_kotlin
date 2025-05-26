plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.android) // Hozzáadva a Hilt plugin
    // Hilt KSP (Kotlin Symbol Processing) plugin. KSP helyettesíti a KAPT-ot.
    id("com.google.devtools.ksp") // Cseréld a Kotlin verziódnak megfelelő KSP verzióra
}

android {
    namespace = "com.example.pillertest2_2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pillertest2_2"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        // Rick and Morty API URL hozzáadása BuildConfig-hez
        // Mivel ez publikus API, nem kell a local.properties-be tenni, de ha titkos lenne, oda tennénk
        buildConfigField("String", "RICK_MORTY_BASE_URL", "\"https://rickandmortyapi.com/api/\"")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true // Engedélyezzük a BuildConfig generálást
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation Component
    implementation(libs.androidx.navigation.compose)

    // ViewModel - LiveData
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler) // KSP processzor
    // ksp(libs.hilt.compiler) // Dagger Hilt compiler (ha Daggerrel használnád)
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0-alpha01")


    // Retrofit for Networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // JSON converter for Retrofit

    // OkHttp for logging network requests (optional, but very useful for debugging)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coil for Image Loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Shared preferences
    implementation("androidx.datastore:datastore-preferences:1.1.1")
}