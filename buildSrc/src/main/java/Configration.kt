package com.revolutan.exchange


object Modules {
    val app = ":app"
    val data = ":data"
    val domain = ":domain"
    val remote = ":remote"
    val presentation = ":presentation"
}

public object Configration {

    object Android {
        const val compileSdk = 28
        const val mindSdk = 21
        const val targetSdk = 28
        const val buildTools = "28.0.3"
        const val versionCode = 1
        const val versionName = "1.0"
        const val kotlinVersion = "1.3.30"
        const val applicationId = "com.revolutan.exchange"
    }

    object RemoteConfig {
        const val DEV_BASE_URL = "\"https://revolut.duckdns.org\""
        const val PRODUCTION_BASE_URL = "\"https://revolut.duckdns.org\""
    }

    object ClassPath {
        private const val gradleVersion = "3.4.0"
        const val gradle = "com.android.tools.build:gradle:$gradleVersion"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Android.kotlinVersion}"
    }

    object RepositoriesURL {
        const val jitpack = "https://jitpack.io"
    }
}

public object Libraries {
    object Network {
        private const val retrofitVersion = "2.5.0"
        private const val okhttpVersion = "3.12.0"
        private const val rxAadapterVersion = "2.6.0"

        const val rxAadapter = "com.squareup.retrofit2:adapter-rxjava2:$rxAadapterVersion"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
        const val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:$okhttpVersion"
    }

    object AndroidXSupport {
        private const val appCompact = "1.0.2"
        private const val materialVersion = "1.0.0-rc01"
        private const val constraintLayoutVersion = "2.0.0-alpha2"
        private const val ktxVersion = "1.0.2"

        const val coreKTX = "androidx.core:core-ktx:$ktxVersion"
        const val appcompat = "androidx.appcompat:appcompat:$appCompact"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        const val material = "com.google.android.material:material:$materialVersion"
    }


    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Configration.Android.kotlinVersion}"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Configration.Android.kotlinVersion}"
    }

    object Koin {
        private const val koinVersion = "2.0.0-rc-2"
        const val koin = "org.koin:koin-android:$koinVersion"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        const val koinTest = "org.koin:koin-test:$koinVersion"
    }

    object RX {
        private const val rxAndroidVersion = "2.1.1"
        private const val rxKotlinVersion = "2.3.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${rxAndroidVersion}"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${rxKotlinVersion}"

    }

    object Testing {

        private const val supportTestRunnerVersion = "1.0.2"
        private const val espressoVersion = "3.1.1"
        private const val mockitoForKotlinVersion = "2.1.0"
        private const val androidXCoreTestingVersion = "2.0.0-alpha1"

    }

}

