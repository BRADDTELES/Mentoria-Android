plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   id("com.google.gms.google-services")
   id("kotlin-kapt")
   id("com.google.dagger.hilt.android")
}

android {
   namespace = "com.danilloteles.aulaifood"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.danilloteles.aulaifood"
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
   kotlinOptions {
      jvmTarget = "1.8"
   }
   buildFeatures{
      viewBinding = true
   }
}

dependencies {

   //Firebase
   implementation(platform(libs.firebase.bom))
   implementation(libs.google.firebase.analytics)
   implementation(libs.firebase.auth)
   implementation(libs.firebase.firestore)
   implementation(libs.firebase.storage)

   //Dependência de máscara
   implementation("io.github.vicmikhailau:MaskedEditText:5.0.3")

   //Dependência de validação
   implementation (libs.easyvalidation.core)

   //Hilt
   implementation("com.google.dagger:hilt-android:2.51.1")
   implementation(project(":core"))
   kapt("com.google.dagger:hilt-android-compiler:2.51.1")

   //Fragment KTX
   implementation(libs.androidx.fragment.ktx)//by viewModels()

   //Ciclo de vida - Lifecycles
   // ViewModel
   implementation(libs.androidx.lifecycle.viewmodel.ktx)
   // LiveData
   implementation(libs.androidx.lifecycle.livedata.ktx)
   // Lifecycles only (without ViewModel or LiveData)
   implementation(libs.androidx.lifecycle.runtime.ktx)

   //SplashScreen
   implementation(libs.androidx.core.splashscreen)

   //Navigation Component
   implementation(libs.androidx.navigation.fragment)
   implementation(libs.androidx.navigation.ui)

   //Picasso
   implementation(libs.picasso)

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
   correctErrorTypes = true
}