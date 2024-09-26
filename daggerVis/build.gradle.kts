plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
    `maven-publish`
}
dependencies{
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.ksp.symbol.processing.api)
    implementation(libs.graphviz.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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