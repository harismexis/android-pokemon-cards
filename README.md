### Overview:

The project is written in Kotlin and uses MVVM Architecture.
Popular Frameworks have been used i.e. JetPack (Coroutines, LiveData, Room, ViewModel), 
Retrofit, Dagger, Mockito etc.

### Description:

The application fetches a json feed from the internet and shows the items in a List. 
When user clicks on a list item, a new screen opens showing details of the selected Item.  

### Tests:

The project contains Unit Tests for testing the business logic of the application.
The tests use Mockito for mocking the dependencies of the classes under test and also
some utilities for setting up the tests with coroutines and live data. Also some fake
network responses from real JSON data have been used. Also Instrumented Tests have been 
added to test the Android related code of the application.

&nbsp;
