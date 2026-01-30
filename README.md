# PixabayEye

An Android application for searching and browsing images from Pixabay using their public API.

---

## Tech Stack

### Core
- Kotlin
- Coroutines & Flow
- Jetpack Compose
- Material3
- Navigation3

### Libraries
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Networking:** [Retrofit 2](https://square.github.io/retrofit/), [Moshi](https://github.com/square/moshi)
- **Database:** Room
- **Pagination:** Paging 3
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/)

### Code Quality
- [ktlint](https://github.com/pinterest/ktlint)
- [detekt](https://detekt.dev/)

### Testing
- JUnit
- [Mockk](https://mockk.io/)
- [Turbine](https://github.com/cashapp/turbine)
- Espresso
- Compose UI Testing
- Hilt Testing

---

## Architecture

The project follows **Clean Architecture** principles with **MVVM** pattern and modular structure.

### Modules

- **`:app`** — Application entry point and dependency injection setup
- **`:domain`** — Business logic layer (pure Kotlin, platform-independent, multiplatform-ready)
- **`:data`** — Data sources, repositories, and caching implementation
- **`:uiSearch`** — Image search feature UI
- **`:uiDetails`** — Image details screen UI
- **`:uiCommon`** — Shared UI components and utilities
- **`:testCommon`** — Shared testing utilities

### Key Features

- Clean Architecture with separation of concerns
- Domain layer is pure Kotlin and multiplatform-ready
- Offline-first approach with Room database caching
- Pagination support using Paging 3 with RemoteMediator
- SSL pinning for enhanced security

---

## Build Configuration

Build logic and conventions are centralized in **`build-logic`**:

- All project dependencies defined in `Dependencies.kt`
- `AndroidLibraryBaseConventionPlugin.kt` — Base configuration for all modules
- `AndroidLibraryUiConventionPlugin.kt` — Additional configuration for UI modules
- `KmpLibraryBaseConventionPlugin.kt` — Base configuration for kmp modules

---

## Development

### Code Quality Checks

Format and lint code:
```bash
./gradlew codeFormatAndCheck
```

Run all tests:
```bash
./gradlew allTest
```

The test suite includes:
- Unit tests
- Android instrumented tests
- Compose UI tests

---

## License

This project is licensed under the **MIT License** — see the [LICENSE](https://github.com/Mepfic/PixabayEye/blob/main/LICENSE) file for details.