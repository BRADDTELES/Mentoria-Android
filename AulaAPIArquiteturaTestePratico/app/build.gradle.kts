plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.jamiltondamasceno.aulaapiarquiteturatestepratico"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jamiltondamasceno.aulaapiarquiteturatestepratico"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //ViewModel e LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Bibliotecas de testes (unitários)
    testImplementation(libs.junit)

    //Truth -> Verificações dos teste
    testImplementation(libs.truth)

    //Mockito -> Dados fictícios para testes
    testImplementation(libs.mockito.core)

    //Coroutines Test (runTest) -> Testa corrotinas de forma mais simplificada
    testImplementation(libs.kotlinx.coroutines.test)

    //Core Testing -> Testar LiveData -> Muda execução LiveData para Síncrona
    testImplementation(libs.androidx.core.testing)

    //Testes Instrumentados
    //Integração(banco de dados) e Interface E2E
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}