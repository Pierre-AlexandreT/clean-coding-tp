# Practical Work

## 1. Initialisation 
- Create a project named `Clean Coding TP` with Android Studio
- Create a Github repository named `clean-coding-tp`
- Init Git in the project root
- Generate a `.gitignore` with [gitignore.io](https://www.gitignore.io)
- Publish the initial commit

## 2. Dependencies
- Update all dependencies
- Update `compileOptions` `kotlinOptions` to target _Java 8_
- Extract all version variable
- Add `kapt` plugin
- Add dependencies like below (in the `app/build.gradle`) : 
```groovy
dependencies {
    // region Embedded Library
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    // endregion

    // region Kotlin - Core
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"
    // endregion

    // region Android - AppCompat
    implementation "androidx.appcompat:appcompat:$appcompat_version"
    // endregion

    // region Android - RecyclerView
    implementation "androidx.recyclerview:recyclerview:$recyclerview_version"
    // endregion

    // region Android - Constraint Layout
    implementation "androidx.constraintlayout:constraintlayout:$constraint_version"
    // endregion

    // region Android - JetPack - Core
    implementation "androidx.core:core-ktx:$core_version"
    // endregion

    //  region Android - JetPack - Lifecycle
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    // endregion

    // region Android - JetPack - Navigation
    implementation "androidx.navigation:navigation-runtime-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"
    // endregion

    // region Android - JetPack - Paging
    implementation "androidx.paging:paging-runtime:$paging_version"
    implementation "androidx.paging:paging-runtime-ktx:$paging_version"
    // endregion

    // region Google - Material Components
    implementation "com.google.android.material:material:$material_component_version"
    // endregion

    // region Networking - Okhttp
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    // endregion

    // region Networking - Retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    // endregion

    // region Networking - Glide
    kapt "com.github.bumptech.glide:compiler:$glide_version"
    implementation "com.github.bumptech.glide:glide:$glide_version"
    // endregion

    // region Testing
    testImplementation "junit:junit:$junit_version"
    androidTestImplementation "androidx.test.ext:junit:$android_junit"
    androidTestImplementation "androidx.test.espresso:espresso-core:$espresso_core_version"
    // endregion
}

```
- Update the version variables (in `./build.gradle`) :
```groovy

ext {

    // region Kotlin Framework
    coroutine_version = '1.3.1'
    // endregion

    // region Android
    appcompat_version = '1.1.0'
    recyclerview_version = '1.1.0'
    constraint_version = '1.1.3'
    // endregion

    //region JetPack
    core_version = '1.2.0-rc01'
    lifecycle_version = '2.2.0-rc02'
    navigation_version = '2.2.0-rc02'
    paging_version = '2.1.0'
    // endregion

    // region Networking
    retrofit_version = '2.6.2'
    okhttp_version = '4.2.2'
    glide_version = '4.10.0'
    // endregion

    // region Google
    material_component_version = '1.2.0-alpha02'
    // endregion

    // region Testing
    junit_version = '4.12'
    android_junit = '1.1.1'
    espresso_core_version = '3.2.0'
    // endregion
}
``` 
- Synchronise the project