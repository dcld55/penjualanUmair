package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.circleImage() {
    implementation("de.hdodenhof:circleimageview:3.1.0")
}