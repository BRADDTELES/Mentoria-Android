plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
}

android {
   namespace = "com.danilloteles.aulajetpackcomposeatualizacao"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.danilloteles.aulajetpackcomposeatualizacao"
      minSdk = 24
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      vectorDrawables {
         useSupportLibrary = true
      }
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
      compose = true
      viewBinding = true
   }
   composeOptions {
      kotlinCompilerExtensionVersion = "1.5.1"
   }
   packaging {
      resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
   }
}

dependencies {

   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.activity.compose)
   implementation(libs.androidx.ui.graphics)
   //Dependências do compose
   val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
   implementation(composeBom)
   androidTestImplementation(composeBom)

   // Choose one of the following:
   // Material Design 3
   implementation("androidx.compose.material3:material3")
   // or Material Design 2
   implementation("androidx.compose.material:material")
   // or skip Material Design and build directly on top of foundational components
   implementation("androidx.compose.foundation:foundation")
   // or only import the main APIs for the underlying toolkit systems,
   // such as input and measurement/layout
   implementation("androidx.compose.ui:ui")

   // Android Studio Preview support
   implementation("androidx.compose.ui:ui-tooling-preview")
   debugImplementation("androidx.compose.ui:ui-tooling")

   // UI Tests
   androidTestImplementation("androidx.compose.ui:ui-test-junit4")
   debugImplementation("androidx.compose.ui:ui-test-manifest")

   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}