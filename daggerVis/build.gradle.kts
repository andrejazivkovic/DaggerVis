plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.power.assert)
    alias(libs.plugins.kotlinBinary)
    id("maven-publish")
}

android {
    namespace = "com.andrejaziv.daggergraphvis"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies{
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.ksp.symbol.processing.api)
    implementation(libs.graphviz.kotlin)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate{
                from(components["release"])
            }
        }
    }
}