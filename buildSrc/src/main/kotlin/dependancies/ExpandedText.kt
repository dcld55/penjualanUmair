package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.expandedText() {
    implementation("com.borjabravo:readmoretextview:2.1.0")
}