plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
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