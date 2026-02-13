# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Android debug build
./gradlew :composeApp:assembleDebug

# Android release build
./gradlew :composeApp:assembleRelease

# Build specific module
./gradlew :<module-name>:build

# Run all tests
./gradlew test

# Run specific module tests
./gradlew :<module>:test

# iOS - open in Xcode
open iosApp/iosApp.xcodeproj
```

## Project Overview

Kotlin Multiplatform (KMP) mobile app for Android and iOS with shared Compose UI. Root package: `kz.mechta`.

**Tech Stack:**
- Kotlin 2.2.20, Compose Multiplatform 1.9.1
- Ktor Client 3.3.1 (OkHttp for Android, Darwin for iOS)
- SQLDelight 2.1.0 for local database
- Koin 4.1.0 for dependency injection
- DataStore for preferences
- Navigation Compose 2.9.0

## Architecture

### Module Structure
```
composeApp/          # App entry point, root navigation, Koin bootstrap
core-data/           # Repositories, network, database, use cases
core-ui/             # Shared Compose components, theme, MVI utilities
core-navigation/     # Navigation routes and shared nav args
feature_*/           # Feature modules (splashscreen, onboarding, main, home, catalog)
iosApp/              # iOS entry point (Swift/Xcode)
```

### MVI Pattern

Features follow MVI with Contract/ViewModel/Page structure:

```kotlin
// Contract defines State, Event, Effect
interface FeatureContract : UnidirectionalViewModel<State, Event, Effect> {
    data class State(...)
    sealed class Event { ... }
    sealed class Effect { ... }
}

// ViewModel implements contract
class FeatureViewModel(...) : ViewModel(), FeatureContract {
    private val mutableState = MutableStateFlow(State())
    override val state = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<Effect>()
    override val effect = effectFlow.asSharedFlow()

    override fun event(event: Event) { ... }
}

// Page uses the `use()` helper from core-ui
@Composable
fun FeaturePage(...) {
    val (state, dispatch, effects) = use(viewModel)
    effects.collectInLaunchedEffect { effect -> ... }
}
```

### Koin DI Structure

Each feature has `di/Koin.kt`. Core modules in `core-data/di/CoreDataModule.kt`:
- `coreNetworkModule` - HttpClient, APIs
- `coreDBModule` - SQLDelight database
- `coreRepositoryModule` - Repository implementations
- `coreUseCaseModule` - Use cases (factory scope)
- Platform-specific: `prefsModule`, `databaseDriverModule` (expect/actual)

### Network Layer

```kotlin
// Use NetworkUtil.safeApiCall for API calls
suspend fun getData(): Resource<T> = NetworkUtil.safeApiCall { api.getData() }

// Resource wrapper for results
sealed class Resource<T> {
    data class Success<T>(val value: T) : Resource<T>()
    sealed class Failure : Resource<Nothing>() {
        data object FailureNetwork : Failure()
        data class FailureServer(val message: String?) : Failure()
    }
}
```

### Database (SQLDelight)

Schema files: `core-data/src/commonMain/sqldelight/kz/mechta/core_data/data/db/*.sq`

Platform drivers configured via expect/actual in `core-data/di/DatabaseDriverModule.kt`.

### Navigation

Type-safe navigation using `@Serializable` route objects. Root routes in `App.kt`, feature routes in respective modules.

## Adding a New Feature

1. Create `feature_<name>` module
2. Add `presentation/` with Contract, ViewModel, Page
3. Add `di/Koin.kt` with viewModel definition
4. Register module in `App.kt` Koin modules
5. Add route and composable to navigation graph

## Platform-Specific Code

Use source sets:
- `commonMain` - Shared code
- `androidMain` - Android-specific (OkHttp engine, Android DataStore)
- `iosMain` - iOS-specific (Darwin engine, iOS DataStore)