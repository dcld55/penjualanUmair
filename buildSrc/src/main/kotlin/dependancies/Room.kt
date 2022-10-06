package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.room() {
    val room_version = "2.3.0"

    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


}