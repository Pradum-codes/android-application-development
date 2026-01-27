# Hilt Integration Guide

### This README shows how Dagger Hilt is used in this mini Android Kotlin project and how to add Hilt to your own project. Examples point to the concrete files in this repository so you can see real usage.

Quick checklist (what this README contains)

- Project Gradle setup (Groovy + Kotlin DSL) and notes about kapt vs ksp
- Minimal example snippets: Application, Activity/Fragment, ViewModel, Module
- Mapping to this project’s source files
- Useful commands and troubleshooting tips

Overview

Dagger Hilt is a dependency injection library built on top of Dagger that simplifies DI in Android apps. This project demonstrates common Hilt patterns:

- @HiltAndroidApp on Application
- @AndroidEntryPoint on Activity / Fragment
- @HiltViewModel for ViewModel injection
- @Module + @InstallIn for providing dependencies

Why this README is useful

- Shows copy-pasteable snippets that match code in this repo
- Describes both Groovy and Kotlin DSL Gradle setups
- Notes gotchas for Compose previews, ViewModel SavedStateHandle, and KSP vs KAPT

1) Gradle setup

This project uses Kotlin DSL and KSP for annotation processing. If you use Groovy build files or prefer kapt, both variants are shown.

Kotlin DSL (example from this project - app/build.gradle.kts)

```
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") // KSP (this project uses ksp)
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.59")
    ksp("com.google.dagger:hilt-compiler:2.59")
    // For instrumentation tests (if needed)
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.59")
}
```

Notes on KSP vs KAPT

- KSP is recommended for pure Kotlin projects (faster, Kotlin-native).
- If using Java annotation processors or libraries that require kapt, apply `kotlin-kapt` and use `kapt` instead of `ksp`.

Groovy (example) - project-level & module-level snippets

```
// Project-level build.gradle (Groovy)
buildscript {
    dependencies {
        classpath "com.google.dagger:hilt-android-gradle-plugin:2.59"
    }
}

// Module-level build.gradle (Groovy)
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt' // if using kapt
    id 'dagger.hilt.android.plugin'
}

dependencies {
    implementation "com.google.dagger:hilt-android:2.59"
    kapt "com.google.dagger:hilt-compiler:2.59" // if using kapt
}
```

2) Application class

Annotate your Application subclass with @HiltAndroidApp. This triggers Hilt’s code generation and creates the application-level component.

Example (this project): `app/src/main/java/com/pradumcodes/notes/NotesApp.kt`

```
package com.pradumcodes.notes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp : Application()
```

Make sure AndroidManifest references your Application class:

```
<application android:name=".NotesApp" />
```

3) Entry points: Activities, Fragments, Compose

Annotate Android components that require injection with @AndroidEntryPoint.

Example Activity (this project): `app/src/main/java/com/pradumcodes/notes/MainActivity.kt`

```
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NotesViewmodel by viewModels()
}
```

Fragments work the same. Use `by viewModels()` or `by activityViewModels()` where appropriate.

Compose previews: avoid using Hilt in preview-only composables. Provide null or fake ViewModels/parameters for previews.

4) ViewModel injection

Use @HiltViewModel on ViewModels and inject dependencies via the constructor.

Example (this project): `app/src/main/java/com/pradumcodes/notes/presentation/NotesViewmodel.kt`

```
@HiltViewModel
class NotesViewmodel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    // repository injected by Hilt
}
```

Notes:

- Add `SavedStateHandle` to constructor if your ViewModel needs saved state — Hilt will provide it automatically.
- Use `by viewModels()` in Activities/Fragments to obtain Hilt-backed ViewModels.

5) Providing dependencies (Modules)

Use @Module + @InstallIn to tell Hilt how to create dependencies. Prefer @Binds for interfaces and @Provides for concrete construction logic.

Example module (this project): `app/src/main/java/com/pradumcodes/notes/di/AppModule.kt`

```
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "notes_db").build()
    }

    @Provides
    fun providesNoteDao(db: NotesDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun providesNotesRepository(dao: NoteDao): NoteRepository = NoteRepositoryImpl(dao)
}
```

Tips on scoping:

- Use @Singleton for app-wide singletons (install in SingletonComponent).
- Match scope to the component you install the module into.

6) Example mapping (where to find things in this repo)

- Application: `app/src/main/java/com/pradumcodes/notes/NotesApp.kt`
- Activity (entry point): `app/src/main/java/com/pradumcodes/notes/MainActivity.kt`
- ViewModel: `app/src/main/java/com/pradumcodes/notes/presentation/NotesViewmodel.kt`
- DI Module: `app/src/main/java/com/pradumcodes/notes/di/AppModule.kt`
- Repository implementation: `app/src/main/java/com/pradumcodes/notes/data/NoteRepositoryImpl.kt`
- Room database + DAO: referenced in `di/AppModule.kt` (search for `NotesDatabase`, `NoteDao`)

7) Common commands

Clean & build

```
./gradlew clean assembleDebug
```

Full rebuild (after annotation changes)

```
./gradlew clean build
```

List dependencies for troubleshooting

```
./gradlew :app:dependencies
```

8) Troubleshooting & common gotchas

- "Hilt: ... is not a component": check your @InstallIn target (e.g., SingletonComponent) and ensure modules are top-level classes or objects.
- Missing annotation processor: if you apply `ksp` in Gradle but add `kapt` dependencies (or vice versa), annotation processing will fail. Stick to one (KSP recommended for Kotlin projects).
- Ensure `android:name` in AndroidManifest points to your @HiltAndroidApp Application subclass.
- For Compose previews: do not call Hilt-injected code directly in @Preview; pass fake/null dependencies.
- If you add/remove annotations, do a full clean build to force code generation: `./gradlew clean build`.

9) References

- Official Hilt docs: https://dagger.dev/hilt/
- Hilt & ViewModel integration: https://developer.android.com/training/dependency-injection/hilt-jetpack

License

This README is based on the sample project in this repository (`app/src/main/java/com/pradumcodes/notes`). Free to use and adapt.
