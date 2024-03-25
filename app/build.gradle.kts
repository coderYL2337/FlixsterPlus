import java.util.*
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
    id ("kotlin-parcelize")
}

/*val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))*/



android {
    namespace = "com.example.flixsterplus"
    compileSdk = 34
    val apikeyPropertiesFile = rootProject.file("apikey.properties")
    val apikeyProperties = Properties()

    if (apikeyPropertiesFile.exists()) {
        apikeyProperties.load(apikeyPropertiesFile.inputStream())
    }

    //load the values from .properties file


    buildFeatures{
        viewBinding=true
        buildConfig=true
    }

    defaultConfig {
        applicationId = "com.example.flixsterplus"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //return empty key in case something goes wrong
        //val apiKey = apikeyProperties.getProperty("API_KEY") ?: ""
        buildConfigField("String", "FLIXSTER_API_KEY", apikeyProperties["FLIXSTER_API_KEY"].toString())
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



dependencies {

    //implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.codepath.libraries:asynchttpclient:2.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    implementation ("androidx.core:core-ktx:1.8.0")

    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("com.google.code.gson:gson:2.9.0")

}

