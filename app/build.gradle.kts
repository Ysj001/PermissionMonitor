plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("bcu-plugin")
}

bcu {
    config { variant ->
        loggerLevel = 2
        modifiers = arrayOf(
            com.ysj.lib.bcu.modifier.aspect.AspectModifier::class.java,
        )
    }
    filterNot { variant, entryName ->
//        false
        entryName.startsWith("kotlin/")
            || entryName.startsWith("kotlinx/")
            || entryName.startsWith("java")
            || entryName.startsWith("org/intellij/")
            || entryName.startsWith("org/jetbrains/")
            || entryName.startsWith("org/junit/")
            || entryName.startsWith("org/hamcrest/")
            || entryName.startsWith("com/squareup/")
            || entryName.startsWith("okhttp")
            || entryName.startsWith("com/ysj/lib/bcu/modifier")
    }
}

android {
    namespace = "com.ysj.demo.pm"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ysj.demo.pm"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        checkReleaseBuilds = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // 只在 debug 下依赖
//    debugRuntimeOnly(project(":lib_permission_monitor"))
    debugRuntimeOnly("io.github.ysj001.lib:permission-monitor:1.0.0")

}