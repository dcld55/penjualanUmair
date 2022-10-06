package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.googleFirebase() {
    implementation(platform("com.google.firebase:firebase-bom:30.3.1"))
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-core:21.1.0")
    implementation("com.google.android.gms:play-services-auth:20.2.0")
    implementation ("com.google.firebase:firebase-auth-ktx")
}