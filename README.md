# CryptoApp 📊📈📉
------------
CryptoApp is a mobile application that allows users to view real-time cryptocurrency prices and details. The app utilizes modern Android development technologies and follows best practices in UI design and architecture to provide an optimal user experience.

## Features 
- **Animated Splash Screen**: When the app starts, a stylish animation is shown on the splash screen.
- **Listing of Cryptocurrencies with Prices**: Users can view a list of cryptocurrencies with their current prices.
- **Search Functionality to Filter Cryptocurrencies by Name**: Users can search for a specific cryptocurrency by its name.
- **Detailed View of Selected Cryptocurrency**: Users can click on a cryptocurrency to see detailed information about it.

## Technologies Used:

- [Jetpack Compose](https://developer.android.com/jetpack/compose): 
   - **Jetpack Compose** is a declarative UI toolkit for building user interfaces in Android. Instead of using XML, UI components are built using Kotlin code. Jetpack Compose is used throughout the app for all screen layouts, state management, and UI elements.

- [Hilt (Dependency Injection)](https://developer.android.com/training/dependency-injection/hilt-jetpack): 
   - **Hilt** is a library that simplifies dependency injection in Android. It ensures that necessary classes (such as repositories and view models) are correctly injected into activities, fragments, and composables, allowing for easier management of dependencies.

- [Navigation Component](https://developer.android.com/guide/navigation): 
   - **Navigation Compose** is used to manage navigation within the app.

- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): 
   - **ViewModel** ensures that UI-related data is managed in a lifecycle-conscious way. This prevents data loss during configuration changes and ensures that UI components are only concerned with UI logic.

- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): 
   - **Kotlin Coroutines** are used for managing asynchronous tasks. For example, when fetching data from a repository, coroutines ensure that the UI doesn't freeze while the data is being fetched. **ViewModelScope** is used to launch coroutines that are tied to the lifecycle of the ViewModel.

- [Retrofit](https://square.github.io/retrofit/): 
   - **Retrofit** is used for making API calls to retrieve cryptocurrency data. The responses from the API are handled by Retrofit instances managed through Hilt.

- [Compose State Management](https://developer.android.com/jetpack/compose/state): 
   - **mutableStateOf** and **remember** are used for state management in the app. These functions ensure that when the state changes, the UI is automatically updated.

- [Material3](https://developer.android.com/jetpack/compose/material3): 
   - **Material3** components are used for UI design to ensure the app follows Google's Material Design guidelines, providing a consistent and visually appealing user interface.

## Preview 

![Image](https://github.com/user-attachments/assets/838912f3-a4b0-433c-b863-c3600e84ad19)
