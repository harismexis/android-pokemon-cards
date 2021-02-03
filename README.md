### Overview:

The project is written in Kotlin and uses MVVM Architecture.
Popular Frameworks have been used i.e. JetPack (Coroutines, LiveData, Room, ViewModel), 
Retrofit, Dagger, Mockito, Mockk.
The application fetches a json feed of items from internet and shows the items in a List.
The items are saved in local storage. When user clicks on a list item a new screen opens 
showing details of the selected Item.

### Description:

The initial screen is the Home Screen which shows a list of Pokemon cards with an
image and brief information for each card. If internet is not connected, then the  
items will be retrieved from the local storage. Home screen also supports swipe to refresh. 
The next screen is the Item Detail Screen which shows a larger image of the selected card 
and more detailed information about it.   

### Tests:

The project contains Unit Tests and Instrumented Tests. 
Some utility classes have been used for setting up the tests with coroutines and live data. 
Also some mock network responses from real JSON data have been used.
The Tests share some common resources which exist under the sharedTest folder.

#### Home
![Alt text](screenshots/home/home-poco-f1-2021-02-03-010025.png?raw=true "app screenshot")

#### Picture of the day - photo
![Alt text](screenshots/detail/detail-poco-f1-2021-02-03-010114.png?raw=true "app screenshot")
