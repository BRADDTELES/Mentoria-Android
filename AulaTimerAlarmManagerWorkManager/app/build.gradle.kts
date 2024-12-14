plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
}

android {
   namespace = "com.app.aulatimeralarmmanagerworkmanager"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.app.aulatimeralarmmanagerworkmanager"
      minSdk = 24
      targetSdk = 33
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
   kotlinOptions {
      jvmTarget = "1.8"
   }
   buildFeatures {
      viewBinding = true
   }
}

dependencies {

   //WorkManager
   val workVersion = "2.9.1"
   //LiveData
   val lifecycle_version = "2.8.7"

   // (Java only)
   implementation("androidx.work:work-runtime:$workVersion")

   // Kotlin + coroutines
   implementation("androidx.work:work-runtime-ktx:$workVersion")

   // LiveData
   implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

   // Lifecycles only (without ViewModel or LiveData)
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}