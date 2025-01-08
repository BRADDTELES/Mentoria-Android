plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   id("com.google.gms.google-services")
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

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}