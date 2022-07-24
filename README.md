# PixabayEye Application

Application allow to find images from service "https://pixabay.com/" by public API.
____

## Tech stack

:large_blue_circle: Kotlin
:large_blue_circle: Coroutines / Flow
:large_blue_circle: [Hilt](https://dagger.dev/hilt/)
:large_blue_circle: [Coil](https://coil-kt.github.io/coil/)
:large_blue_circle: [Retrofit 2](https://square.github.io/retrofit/), [Moshi](https://github.com/square/moshi)
:large_blue_circle: Room, Paging
:large_blue_circle: ViewBinding, Navigation

- [X] Testing:
:large_blue_circle: JUnit
:large_blue_circle: [Mockk](https://mockk.io/)
:large_blue_circle: [Turbine](https://github.com/cashapp/turbine)
:large_blue_circle: Espresso
:large_blue_circle: Hilt testing

- [X] Plugins:
:large_blue_circle: [ktlint](https://github.com/pinterest/ktlint)
:large_blue_circle: [detekt](https://detekt.dev/)

## Architecture approaches

:large_blue_circle: MVVM
:large_blue_circle: Modules
    :white_circle: ```:app```
    :white_circle: ```:uiSearch```
    :white_circle: ```:uiDetails```
    :white_circle: ```:uiCommon```
    :white_circle: ```:domain```
    :white_circle: ```:data```
    :white_circle: ```:testCommon```

:large_blue_circle: buildSrc folder to place general conventions that can be used in any modules
:large_blue_circle: all project dependencies described in Dependencies.kt file (placed in buildSrc folder)
:large_blue_circle: androidLibraryBaseConvention.gradle.kts file contains dependencies for any modules
:large_blue_circle: androidLibraryUiConvention.gradle.kts file contains dependencies for any UI modules, extend androidLibraryBaseConvention

:large_blue_circle: caching based on the Room DB, with Paging 3, used RemoteMediator 

## Code quality
:large_blue_circle: Ktlint plugin to format & check style 
:large_blue_circle: Detekt plugin to check style
:large_blue_circle: Unit tests 
:large_blue_circle: Instrumental tests

## Gradle tasks for local checks and on CI/CD system

:arrow_forward: Run code format and check code style:
```
./gradlew codeFormatAndCheck
```

:arrow_forward: Run all Unit & UI tests:
```
./gradlew allTest
```

## Security points
:large_blue_circle: SSL-pinning
:large_blue_circle: API key placed in 
``` rootDir/buildSrc/src/keys/apikeys.properties ``` 
and on the real project file should be added to .gitignore 