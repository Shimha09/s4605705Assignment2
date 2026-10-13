# s4605705Assignment2

NIT3213 Mobile Application Development - Assignment 2

## Overview

This Android application demonstrates API integration, RecyclerView implementation,
Navigation Component, ViewModel architecture, dependency injection using Hilt,
and unit testing.

The application contains three main screens:

1. Login
2. Dashboard
3. Details

## Login

The user logs in using:

- Student ID as the username
- First name as the password

The application sends a POST request to the Footscray authentication endpoint.

On successful authentication, the API returns a keypass which is passed to the
Dashboard screen.

## Dashboard

The Dashboard uses the keypass received from login to request data from the API.

The returned entities are displayed using a RecyclerView.

Each RecyclerView item displays:

- Name
- Architect
- Location
- Year completed
- Style
- Height

The description is not displayed on the Dashboard.

Selecting an item navigates to the Details screen.

## Details

The Details screen displays all information about the selected architecture entity,
including its description.

## API

Base URL:

https://nit3213apinew.onrender.com

Authentication endpoint:

POST /footscray/auth

Dashboard endpoint:

GET /dashboard/{keypass}

## Technologies and Libraries

- Kotlin
- Android Views and XML
- Retrofit
- Moshi
- OkHttp
- RecyclerView
- Android Navigation Component
- ViewModel
- StateFlow
- Kotlin Coroutines
- Hilt Dependency Injection
- JUnit
- MockK
- kotlinx-coroutines-test

## Project Structure

The application is organised into the following packages:

- adapter
- di
- model
- network
- repository
- ui
- viewmodel

## Dependency Injection

Hilt is used for dependency injection.

The NetworkModule provides:

- Moshi
- OkHttpClient
- Retrofit
- ApiService

The repository is injected into the ViewModels using Hilt.

## Unit Testing

Unit tests are implemented for the LoginViewModel and DashboardViewModel.

The tests cover:

- Successful login
- Failed login
- Successful dashboard request
- Failed dashboard request

MockK is used to mock the repository so unit tests do not depend on the real network.

## How to Build and Run

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync and download the required dependencies.
4. Start an Android emulator or connect an Android device.
5. Run the application from Android Studio.
6. Enter the student ID without the letter "s".
7. Enter the student's first name with the correct capitalization.
8. Press Login.
9. Select an entity from the Dashboard to view its Details screen.

## Testing

To run the unit tests:

1. Open the `test` package in Android Studio.
2. Open `LoginViewModelTest` or `DashboardViewModelTest`.
3. Click the green Run button next to the test class.
4. Confirm that all tests pass.

## Author

Student ID: s4605705
Unit: NIT3213 Mobile Application Development