plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.xiaolu.basis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xiaolu.basis"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("int", "VERSION_CODE", versionCode.toString())
        buildConfigField("String", "VERSION_NAME", "\"$versionName\"")
    }

    // 配置 Lint 选项，忽略指定检查
    lint {
        // 禁用指定的 Lint 检查规则
        disable += "NullSafeMutableLiveData"
        // 配置 Lint 检查遇到错误时不停止构建
        checkReleaseBuilds = false
        // 配置 Lint 检查将错误视为警告
        warningsAsErrors = false
    }

    signingConfigs {
        create("release") {
            keyAlias = "basis"
            keyPassword = "123456"
            // 使用项目相对路径指定签名文件
            storeFile = file(rootProject.projectDir.resolve("app/basis.jks"))
            storePassword = "123456"
        }
        getByName("debug") {
            keyAlias = "basis"
            keyPassword = "123456"
            // 使用项目相对路径指定签名文件
            storeFile = file(rootProject.projectDir.resolve("app/basis.jks"))
            storePassword = "123456"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("Boolean", "DEBUG", "true") // 改为 true
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("Boolean", "DEBUG", "false")
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

    buildFeatures {
        viewBinding = true
        buildConfig = true // 启用 BuildConfig 生成
    }
    applicationVariants.all {
        outputs.forEach { output ->
            val outputImpl = output as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            val versionName = versionName ?: "1.0.0"  // 默认版本名
            outputImpl.outputFileName = buildString {
                append("app_v${versionName}")
                append(".apk")
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.utilcodex)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.okhttp)
    implementation(libs.net)
    implementation(libs.xxpermissions)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.viewbinding.ktx)
    implementation(libs.immersionbar)
    implementation(libs.immersionbar.ktx)
    implementation(libs.immersionbar.components)
    implementation(libs.multilanguages)
    implementation(libs.fragment.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.tooltip)
    implementation(libs.mmkv)
    implementation(libs.kotlin.reflect)
    implementation(libs.androidx.draganddrop)
    implementation(libs.zxing.lite)
    implementation(libs.wheelPicker)
}