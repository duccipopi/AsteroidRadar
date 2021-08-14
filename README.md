Asteroid Radar
===============
Second project from [Android Kotlin Developer](https://www.udacity.com/course/android-kotlin-developer-nanodegree--nd940)

Project Instructions
=====================

You are provided with a [starter code](https://github.com/udacity/nd940-android-kotlin-c2-starter/tree/master/starter) in our github repo, which includes the necessary dependencies and plugins that you need to complete this project.

Included in the starter project are:

- *Retrofit* library to download the data from the Internet.
- *Moshi* to convert the JSON data we are downloading to usable data in the form of custom classes.
- *Picasso* library to download and cache images (You could also use *Glide*, but we found it has some issues downloading images from this particular API).
- `RecyclerView` to display the asteroids in a list.

We recommend you following the guidelines seen in the courses, as well as using the components from the Jetpack library:

- `ViewModel`
- `Room`
- `LiveData`
- Data Binding
- Navigation

To start follow the next basic steps:

- Clone the Repo.
- Open the project with Android Studio.
- Run the project and check that it compiles correctly.


Summarized tasks
-----------------
The application you will build must:

- Include `Main` screen with a list of clickable asteroids as seen in the provided design using a `RecyclerView` with its adapter. You could insert some fake manually created asteroids to try this before downloading any data.
- Include a `Details` screen that displays the selected asteroid data once it’s clicked in the `Main` screen as seen in the provided design. The images in the details screen are going to be provided with the starter code: an image for a potentially hazardous asteroid and another one for the non-hazardous ones, you have to display the correct image depending on the `isPotentiallyHazardous` asteroid parameter. Navigation xml file is already included with starter code.
- Download and parse the data from NASA NeoWS (Near Earth Object Web Service) API. As this response cannot be parsed directly with *Moshi*, we are providing a method to parse the data “manually” for you, it’s called `parseAsteroidsJsonResult` inside `NetworkUtils` class, we recommend trying for yourself before using this method or at least take a close look at it as it is an extremely common problem in real-world apps. For this response we need `retrofit-converter-scalars` instead of *Moshi*, you can check this dependency in `build.gradle (app)` file.
- When asteroids are downloaded, save them in the local database.
- Fetch and display the asteroids from the database and only fetch the asteroids from today onwards, ignoring asteroids before today. Also, display the asteroids sorted by date (Check SQLite documentation to get sorted data using a query).
- Be able to cache the data of the asteroid by using a `Worker`, so it downloads and saves today's asteroids in background once a day when the device is charging and wifi is enabled.
- Download Picture of Day JSON, parse it using *Moshi* and display it at the top of `Main` screen using *Picasso* Library. (You can find *Picasso* documentation here: https://square.github.io/picasso/). You could use *Glide* if you are more comfortable with it, although be careful as we found some problems displaying NASA images with *Glide*.
- Add content description to the views: Picture of the day (use the title dynamically for this), details images and dialog button. Check if it works correctly with *talk back*.
- Make sure the entire app works without an internet connection.


Bonus Tasks
------------
The following are bonus tasks to challenge yourself, although they are not going to be taken into account for grading your project, you can implement them to learn more:

1. Modify the app to support multiple languages, device sizes, and orientations.
2. Make the app delete asteroids from the previous day once a day using the same workManager that downloads the asteroids.
3. Match the styles for the details screen subtitles and values to make it consistent, and make it look like what is in the designs.