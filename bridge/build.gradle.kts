plugins {
    id("com.android.application")
}

android {
    namespace = "dev.jkool.chatgptbutton"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.jkool.chatgptbutton"
        minSdk = 32
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
