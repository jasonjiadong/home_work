// Top-level build file where you can add configuration options common to all sub-projects/modules.
//这里添加classpath，一定要放到plugins{}上方
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
}