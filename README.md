# PixabayEye Application

Application allow to find images from service "https://pixabay.com/" by public API.
____

## Tech stack

:large_blue_circle: Libs:
- Kotlin    
- Coroutines / Flow    
- [Hilt](https://dagger.dev/hilt/)    
- [Coil](https://coil-kt.github.io/coil/)    
- [Retrofit 2](https://square.github.io/retrofit/), [Moshi](https://github.com/square/moshi)    
- Room, Paging    
- ViewBinding, Navigation    

:large_blue_circle: Testing:
- JUnit    
- [Mockk](https://mockk.io/)    
- [Turbine](https://github.com/cashapp/turbine)    
- Espresso    
- Hilt testing    

:large_blue_circle: Plugins:
- [ktlint](https://github.com/pinterest/ktlint)    
- [detekt](https://detekt.dev/)    

## Architecture approaches

:large_blue_circle: MVVM    
:large_blue_circle: Modules    
    - ```:app```        
    - ```:uiSearch```    
    - ```:uiDetails```    
    - ```:uiCommon```    
    - ```:domain```    
    - ```:data```    
    - ```:testCommon```    

:point_right: buildSrc folder to place general conventions that can be used in any modules    
:point_right: all project dependencies described in Dependencies.kt file (placed in buildSrc folder)    
:point_right: androidLibraryBaseConvention.gradle.kts file contains dependencies for any modules    
:point_right: androidLibraryUiConvention.gradle.kts file contains dependencies for any UI modules, extend androidLibraryBaseConvention    

:point_right: caching based on the Room DB, with Paging 3, used RemoteMediator    

## Code quality    
:point_right: Ktlint plugin to format & check style     
:point_right: Detekt plugin to check style    
:point_right: Unit tests    
:point_right: Instrumental tests    

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
:point_right: SSL-pinning    
:point_right: API key placed in 
``` rootDir/buildSrc/src/keys/apikeys.properties ``` 
and on the real project file should be added to .gitignore 