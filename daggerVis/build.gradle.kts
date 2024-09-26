plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    `maven-publish`
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
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8" // Set JVM target version
    }
}

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.ksp.symbol.processing.api)
    implementation(libs.graphviz.kotlin)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components.findByName("release") ?: components.findByName("debug"))
            groupId = "com.github.andrejazivkovic"
            artifactId = "daggerVis"
            version = "1.0.8"
        }
    }
}
