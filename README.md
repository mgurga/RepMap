# RepMap
Interactive map to find your state representatives. 
The app displays a restricted map that can only zoom in and out on the United States and on click will display local state representatives.
The results are not 100% accurate and errors might arise, to find 100% accurate information go to [house.gov](https://www.house.gov/representatives/find-your-representative).
RepMap uses [OpenStreetMap](https://www.openstreetmap.org/about) and an android library called [osmdroid](https://github.com/osmdroid/osmdroid) to display a map of the United States.

## Screenshots
<img alt="USA Map" src="https://github.com/mgurga/RepMap/blob/master/docs/usa.png" width="300px"> <img alt="California Districts" src="https://github.com/mgurga/RepMap/blob/master/docs/californiadistricts.png" width="300px"> 

<img alt="Florida" src="https://github.com/mgurga/RepMap/blob/master/docs/florida.png" width="300px"> <img alt="Florida Districts" src="https://github.com/mgurga/RepMap/blob/master/docs/floridadistricts.png" width="300px">

## Setup
1. Checkout the code by running:
```
git clone https://github.com/mgurga/RepMap
```
2. View, edit, and run the source code by using [Android Studio](https://developer.android.com/studio).
3. This app requires [repapi](https://github.com/mgurga/repapi) to be running.
The API can either run on the same computer as the virual device or you can run the API on your own server and replace the IP in the [code](https://github.com/mgurga/RepMap/blob/master/app/src/main/java/com/example/myapplication/GetFromAPI.java#L52).

## Credits
[bhild](https://github.com/bhild) - intigrating osmdroid, mapping zipcodes to state, and setting up other APIs.

[mgurga](https://github.com/mgurga) - creating [repapi](https://github.com/mgurga/repapi) and district info page.
