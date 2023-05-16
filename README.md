# RestaurantBuddy Android

This application will serve as a basic client for the customer to place orders on the RestaurantBuddy API.

## Prerequisites

Although this project is intended to be as easy to set up as possible, some fields need to be modified in order for you
to use this app in your restaurant.

### External API Dependencies

First of all, this app requires a Firebase project to be set up for it. This can be added by following the official
instructions on the [Google Firebase website](https://firebase.google.com/).

Second, the project requires a Google Maps API key to be added to it. This key must be stored in the _local.properties_
file in the root directory of the project under the _MAPS_API_KEY_ variable. You should not have to add the key to the
android manifest file, since the manifest file will point to the properties file. Instructions for how to setup Google
Maps in Android can be found on
the [Google Maps developer documentation](https://developers.google.com/maps/documentation/android-sdk/start).

### Internal Settings

This project's is set up to point to an API on the same host that an Android emulator is running on. Assuming you have
registered an IP address and have an instance of the RestaurantBuddy API running on it, you can change the IP address
found in the AppConfig object in the class. Then, all new requests will point to the updated API in the application.

Finally, any additional rebranding that you would like to perform to tweak the app to your liking can be done. It might
make sense to change the default icon on the login screen to your liking if setting up this project for a real-world
restaurant.

## Licensing and Contributions

Usage of this project is subject to the terms of the MIT license found in the project. Pull requests and contributions
to the project are accepted, but they will also be subject to the terms of the MIT license.
