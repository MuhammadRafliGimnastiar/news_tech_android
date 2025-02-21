plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.safeArgs)

}

android {
    namespace = "com.gimnastiar.newsapple"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gimnastiar.newsapple"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://newsapi.org/v2/\"")
        buildConfigField("String", "API_KEY", "\"eaf12074a2604402bde12961079798be\"")
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.constraintlayout)

    // Room
    api(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    api(libs.logging.interceptor)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Lifecycle
    api(libs.lifecycle.livedata.ktx)

    // Activity and Fragment KTX
    api(libs.activity.ktx)
    api(libs.fragment.ktx)

    implementation(libs.cardview)
    implementation(libs.recyclerview)
    implementation(libs.material)
    implementation(libs.glide)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Paging
    implementation(libs.androidx.paging.runtime.ktx)

    //Shimmer
    implementation(libs.facebook.shimmer)

    //webview
    implementation("androidx.webkit:webkit:1.12.1")


}