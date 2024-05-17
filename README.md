# GithubCruise Android App

A user-friendly GitHub app for anyone, allowing smooth navigation of users and repositories.

This project brings together modern tools and best practices in Android development to create a robust and user-friendly app. It uses libraries like Retrofit for networking, Hilt for dependency injection, and Jetpack Compose for building sleek interfaces. The app features various screens, including a dynamic splash screen, a user list with essential details, and a detailed view of user repositories. Pagination is smoothly implemented to handle large data sets effectively. Thorough testing with JUnit and other tools ensures code quality. The project also focuses on localization, ViewModel usage, and efficient UI state management. Detailed guides for setup, execution, and deployment are included, along with comprehensive API documentation. Lastly, a TODO list outlines planned improvements for future updates.

# Check out demo screen shots and videos here
## Details [demo-screenshots-video](https://github.com/dinkar1708/GithubCruise/tree/main/screenshots)

## Table of Contents
1. [Setup](#setup)
2. [Guide to Run Code](#guide-to-run-code)
3. [API Used](#api-used-in-the-project)
4. [Features](#features)
5. [Run Configuration Guide](#run-configuration-guide)
6. [Testing Guide](#testing-guide)
7. [Coding Guide](#coding-guide)
8. [Release Guide](#release-guide)
9. [APIs](#apis)
10. [FAQ](#faq)
11. [DO/DON'T](#dodont)
12. [TODOs](#todo)

## Setup

### Prerequisites
- JAVA & Android SDK installed - Current tested java SDK 17
- Android Studio
- Emulator / Physical device for testing

### Installation
1. Clone the repository
    ```sh
    git clone https://github.com/dinkar1708/GithubCruise
    ```
2. Open the project in Android Studio
3. Make sure you have the version of Android Studio and JDK specified in Prerequisites section.
4. Build the project
5. Run the project on an emulator or a physical device

## Guide to Run Code

### Run Debug version
- Select release and run. Screenshot is below
### Run Release version
- Select release and run. Screenshot is below

### Special features

**Light theme & Dark Theme support**
- My app offers both light and dark theme options, providing users with a comfortable viewing experience in any lighting condition.
- Easily switch between light and dark themes in the app settings to suit your preference.
- See the screenshots below to get a glimpse of how my app looks in both light and dark themes.

**Localization - Japanese and English language supported**
- My app supports localization in both Japanese and English languages, catering to a diverse user base.
- Seamlessly switch between languages within the app settings to enjoy content in your preferred language.
- Check out the screenshots below to see how my app adapts to different language settings.

**Developed to run on all devices, regardless of size**
- My app is designed to provide a seamless experience on both small and large devices, ensuring optimal usability across a wide range of screen sizes.
- Whether you're using a compact smartphone or a large tablet, my app adapts effortlessly to your device's display, delivering a consistent and intuitive user experience.

**Screen rotation support**
My app supports screen rotation and preserves user search data nicely.

<hr/>

## Features

### スプラッシュス覧画面

スクリーンショットは以下の通りです。
<img width="400" alt="Screenshot 2024-05-17 at 22 57 27" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/9a461fea-2949-40aa-8df1-ccebecc2a263">

**Feature: Splash**

- Display the gradient background.
- Display an animated text.
- After 3 seconds go to user list screen.

**Additional:**
- NA

<hr/>
### ユーザー一覧画面

スクリーンショットは以下の通りです。
<img width="400" alt="Screenshot 2024-05-17 at 22 57 27" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/d337de1d-b9c7-4dd7-a5dc-f565e1d32982">

**Feature: Search Users**

- Display the user list as a list.
- Elements required for each line:
    - Icon image
    - User name
- Selecting each line moves to the user repository screen.
- Display a search string input column at the top of the screen, fixed at the top.
- Display a list of users below the input field based on the input string.

**Additional:**
- Used pagination.
- Display score of the user.
<hr/>

### ユーザーリポジトリ画面

スクリーンショットは以下の通りです。
<img width="400" alt="Screenshot 2024-05-17 at 22 57 27" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/de07c837-04ae-4c25-a8c9-e8e70c1efc31">

**Feature: User repository and details**

1. Displays detailed user information at the top of the list.
    - Elements required:
        - Icon image
        - User Name
        - Full name
        - Number of followers
        - Number of following
2. List repositories of users who are not forking repositories below
    - Elements required:
        - Repository name
        - Repository language
        - Number of stars
        - Description
    - Tap a line in the repository list to display the repository URL in WebView
      **Additional:**
- Filter repositories by fork status using the switch button.
<hr/>

### リポジトリの詳細
スクリーンショットは以下の通りです。
<img width="400" alt="Screenshot 2024-05-17 at 22 57 27" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/b23321ee-1050-48f8-84a0-97f0ab597f95">

**Feature: User repository in web view**

1. Display the user repository inside the app's web view.

**Additional:**
- NA.
<hr/>

# Run Configuration Guide

## Follow screen shot of android studion and run

**Run debug version**
- Config details
```
        release {
            manifestPlaceholders += mapOf("app_name" to "GithubCruise")
            buildConfigField("boolean", "DEBUG", "false")
            buildConfigField("String", "API_BASE_URL", "\"https://release.api.github.com\"")
            buildConfigField("String", "API_VERSION", "\"2022-11-28\"")
            // un comment it to run release build to test only using android studio
//            signingConfig = signingConfigs.getByName("debug")
        }
```
- Screen shot
  <img width="521" alt="Screenshot 2024-05-17 at 22 17 12" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/bc102a74-4583-4e73-86fb-1a33a99aa209">


**Run release version**
- Config details
```
        debug {
            manifestPlaceholders += mapOf("app_name" to "DebugGithubCruise")
            buildConfigField("boolean", "DEBUG", "true")
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
            buildConfigField("String", "API_VERSION", "\"2022-11-28\"")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "debug"
        }
```
- Screen shot
  <img width="524" alt="Screenshot 2024-05-17 at 22 17 30" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/cb9355bb-e505-452c-901e-b3153018b438">

# Testing Guide

## Write test case
- General - https://developer.android.com/training/testing
- Guide for unit test case - https://developer.android.com/training/testing/local-tests
- Guide for ui test case - https://developer.android.com/training/testing/instrumented-tests/ui-tests

## Run test case
**How to run test cases from UI**
<img width="547" alt="Screenshot 2024-05-17 at 22 55 46" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/28d3aa8a-8810-44a5-a3e8-644126b3cf61">
<img width="1043" alt="Screenshot 2024-05-17 at 23 03 59" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/428b7a14-0407-4597-a5b6-2a9bd7a77830">

**How to run test cases using command**
**Arnav is my son name hahaha**
<img width="1077" alt="Screenshot 2024-05-17 at 22 57 27" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/7f790bc1-f792-4aa9-ac1c-a7bd02afa7d2">

# Coding Guide
## Test Case Document
- [Testing Kotlin Coroutines](https://developer.android.com/kotlin/coroutines/test)
- [Inject Scope](https://developer.android.com/kotlin/coroutines/test#inject-scope)
- General Guide:
    - [Local Tests](https://developer.android.com/training/testing/local-tests)
    - [Testing Flows](https://developer.android.com/kotlin/flow/test)
## Coding
- **Effortless Maintenance with MVVM Design Pattern**
  Our app employs the MVVM (Model-View-ViewModel) design pattern, ensuring easy addition of new features and bug fixes without disrupting existing functionality.With MVVM, the codebase remains organized and easy to understand, streamlining development and maintenance tasks.By leveraging the latest libraries and Android SDK platform features, our app delivers a user-friendly experience on all devices, big or small. Check out the screenshots below to see how our app seamlessly integrates MVVM architecture for smooth functionality and hassle-free maintenance.
  <img width="516" alt="Screenshot 2024-05-17 at 23 22 26" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/f4673fcc-3e21-4f0c-a611-fb10767601da">
  <img width="554" alt="Screenshot 2024-05-17 at 23 21 33" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/41e2fdf9-3369-4d42-94b6-69a1ffce6f6c">

-  MVVM - https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#7
- [Localization](https://developer.android.com/training/basics/supporting-devices/languages)
- [Use ViewModel at Screen Level](https://youtu.be/pCX9wvu-Bq0?t=470)
- [UI State](https://developer.android.com/topic/architecture/ui-layer/stateholders#ui-state)
- [UI State (StateFlow)](https://developer.android.com/topic/architecture/ui-layer/state-production#stateflow_3)
- Current Navigation Controller:
    - [JetNews Navigation Graph](https://github.com/android/compose-samples/blob/master/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsNavGraph.kt)
    - [TodoApp Navigation Graph](https://github.com/android/architecture-samples/blob/main/app/src/main/java/com/example/android/architecture/blueprints/todoapp/TodoNavGraph.kt)
    - Run Release Build:
        - In build.gradle, uncomment the following to run release build for testing only:
          ```gradle
          // signingConfig = signingConfigs.getByName("debug")
          ```
- My app offers seamless UI development with a live preview feature, enabling rapid iterations and easy adjustments. With this functionality, developers can effortlessly write and modify UI components in seconds, streamlining the development process. Check out the screenshots below to see how my app simplifies UI development.
  <img width="932" alt="image" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/ca3d0f4e-119f-4708-ae4c-c2a265a3bb5d">



- [ViewModel Test Guide](https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel#3)
- [Typography Guide](https://developer.android.com/reference/kotlin/androidx/compose/material3/Typography)
    - [Material 3 Typography Overview](https://m3.material.io/styles/typography/overview)

## App navigation
- Follow official documentation [Android Navigation](https://developer.android.com/develop/ui/compose/navigation)

## Package used
### App Dependencies
- **Timber**: A logging utility that simplifies and enhances logging in Android applications.

### Architecture Components
- **Material 3**: Implements the latest Material Design principles for a modern UI experience.
- **Retrofit**: A robust HTTP client for making API requests in a type-safe manner.
- **Retrofit Converter (Moshi)**: Converts JSON responses into Kotlin objects efficiently using Moshi.
- **Logging Interceptor**: Helps in logging HTTP request and response data for debugging purposes.
- **Moshi Kotlin**: A JSON parsing library tailored for Kotlin, making JSON parsing a breeze.
- **MockK**: A mocking library specifically designed for Kotlin, aiding in unit testing.
- **Coil**: An image loading library optimized for Kotlin Coroutines and seamlessly integrated with Jetpack Compose.

### Dependency Injection
- **Hilt**: A dependency injection framework that streamlines dependency injection setup in Android apps.
    - **Hilt Android Core**: Core components of the Hilt library for Android.
    - **Hilt Navigation Compose**: Integration of Hilt with Jetpack Compose's navigation system.
    - **Hilt Compiler**: Annotation processor required for using Hilt in your project.

### Jetpack Compose
- **Compose BOM**: Manages versioning of Jetpack Compose libraries effectively.
- **Activity Compose**: Enables integration of Compose with Android's activity lifecycle.
- **Compose Compiler**: Enhances Compose development by providing a dedicated compiler plugin.
- **Compose Foundation Core**: Provides fundamental building blocks for creating Compose UIs.
- **Compose UI Tooling Preview**: Tools for previewing and debugging Compose UIs during development.
- **Navigation Compose**: Navigation library tailored for Jetpack Compose applications.
- **Lifecycle Runtime Compose**: Seamless integration of Jetpack Compose with Android's lifecycle.
- **ViewModel Compose**: Simplifies ViewModel integration in Jetpack Compose applications.

### Debug Dependencies
- **Compose UI Tooling Core**: Core tools for Compose development, including preview and inspection capabilities.
- **Compose UI Test Manifest**: Manifest file required for testing Compose UI components in debug builds.

### Testing Dependencies
- **JUnit 4**: A widely-used testing framework for writing and executing unit tests in Java applications.
- **Kotlin Coroutines Test**: Test utilities for verifying the behavior of Kotlin Coroutines.
- **Android Test Implementation**: Dependencies required for writing and running Android-specific unit tests.

## Guide to inspect network performance
**From bottom click app inspection while the app is running**
<img width="1906" alt="Screenshot 2024-05-17 at 22 27 37" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/c1fbaea1-57aa-43b2-b4b3-9b849db00164">

**Select from left side to see network details. See below picture**
<img width="1885" alt="Screenshot 2024-05-17 at 22 31 31" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/4c6516e3-ceeb-4e7f-ac36-b60922e9a17b">
## Setting Author template
<img width="983" alt="Screenshot 2024-05-17 at 22 44 56" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/a7c21ec7-ce37-4a10-891a-ab1ed046f70a">

# Release Guide
**Guide CI/CD For detailed guidance**
- Github actions is used and it is passed details is written in this pr. https://github.com/dinkar1708/GithubCruise/pull/12
- Refer to: [GitHub Actions Quickstart](https://docs.github.com/en/actions/quickstart)
  **Github Actions (CI/CD)**
### How to Use This Repository's .yml File:
- Simply copy and paste the `build.yml` file into your repository under `.github/workflows/build.yml`, ensuring to specify the correct version of the Flutter SDK, and it will automatically start building.

# APIs
## API Used in the project
### GitHub API:
- Base URL: `https://api.github.com/`
#### API Details [documentation](https://github.com/dinkar1708/GithubCruise/tree/main/documentation)

**User List API**
- **Doc:** https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-users
- **URL:** `https://api.github.com/search/users?q="dinkar1708"`(with pagination // https://api.github.com/search/users?q=dinkar1708&page=1&per_page=1)
  **User profile**
- **Doc:** https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user
- **URL:** `https://api.github.com/users/dinkar1708`
  **User repositories**
- **Doc:** - https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user
- **URL:** `https://api.github.com/users/dinkar1708/repos?q=page=1&per_page=2`

# FAQ
- Supported 99.6% of market devices by using minimum 21 api level
  <img width="825" alt="Screenshot 2024-05-17 at 15 37 05" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/a6bc0aa7-cd6f-42a6-ae5f-d14769f1e991">

- API pagination guide - [Using Pagination in the REST API](https://docs.github.com/en/rest/using-the-rest-api/using-pagination-in-the-rest-api?apiVersion=2022-11-28)

- Find documentation -<img width="528" alt="Screenshot 2024-05-17 at 22 53 33" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/bc887d42-ec7b-409d-9b6c-e54da53859df">

- Run debug and release on same device together. See screen shots

<img width="500" alt="Screenshot 2024-05-17 at 22 53 33" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/b361028e-4aac-4107-8642-238de57bbeff">

<img width="500" alt="Screenshot 2024-05-17 at 22 53 33" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/04b6cb8b-7141-4163-8e03-2c0606bb3424">

<img width="500" alt="Screenshot 2024-05-17 at 22 53 33" src="https://github.com/dinkar1708/GithubCruise/assets/14831652/3b3faefd-9aaf-4f69-836d-9e7feb642747">


# DO/DON'T
1.

# TODO
- Fix app display name for debug and release apps.
- Implement search functionality for users on keyboard press events in the user list page, utilizing debounce and throttling techniques for efficient handling of input.
- Write UI test - https://developer.android.com/training/testing/instrumented-tests/ui-tests.
- Implement caching and refresh strategy for repository list page; add refresh button for manual API update; modify toggle to filter data from cache when on.
- Test the app on various screen sizes, including both small and large devices, to ensure optimal performance and UI compatibility. Address any UI bugs that may arise during testing.
- Conduct thorough testing on Android tablets to identify and rectify any remaining issues or bugs specific to tablet devices.
- **Localization in ViewModel**
    - Change string error messages to int and use them like:
      ```kotlin
      xxx.setValue(R.string.labelString)
      ```
- Fix app icons
    - fix rounded icon
- **User Repositories API Pagination support**
  When utilizing pagination in the user repositories API, it's essential to determine the total count of API results for effective navigation. Follow these steps to discover how to retrieve the total count:
  #### Step 1
  Access the following API endpoint:
  ```plaintext
  https://api.github.com/users/dinkar1708/repos?q=page=1&per_page=2
  ```
  This endpoint will return 2 results. However, it doesn't provide information about the total count of results.
  #### Step 2
  To obtain the total count, use the following API endpoint:
  ```plaintext
  https://api.github.com/users/dinkar1708/repos
  ```
  This endpoint will return 28 results, indicating that there is data available. By comparing the results from Step 1 and Step 2, you can discern the total count. Knowing the total count facilitates proper utilization of pagination.

- **Verify Code Coverage**
    - [Code Coverage Guide](https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel#4)

- **Advance Screen Navigation**
    - Try a common controller: [Common Controller Example](https://github.com/android/nowinandroid/blob/main/app/src/main/kotlin/com/google/samples/apps/nowinandroid/ui/interests2pane/InterestsListDetailScreen.kt)
