plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.qabdallh.qureoassistant"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.qabdallh.qureoassistant"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }
}
