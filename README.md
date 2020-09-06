# ShareTheRation

This android application aims to establish a connection as well to spread awareness among people about the NGO's in the nearby places around them. We believe that this application can help substantially in our goal to zero hunger and can be a real game changer in this field.

##### Requirements: Android Studio, Nodejs, npm
## Installation Guide:
After ensuring that you have installed the requirements in your machine follow the below steps:

* Clone the Repo into your local machine(master branch)
* Open the project in the Andriod Studio and make the subsequent changes:
  * Open the *app/res/values/strings.xml*
  * On line 12 change the "url" with the IP-address(For more Info check FAQ's)
  * Open the *app/res/values/google_maps_api.xml*
  * follow the steps given there to place your google maps api key
* Open the *SWE_Project/backend* in your machine editor's
  * run *npm install* for installing the dependencies 
  * Open the server.js and change the IP-address written on line 102 in the following way
    "location": http://<**IP-address**>:8000... 
    **Note:** the IP-address should be same as what you wrote in earlier steps
 
 * With this you are good to go to run the app. In case you face problem, please let us know by creating an issue in the Repository.

## Starting the Application

* Start the nodejs server
  * Go to the *SWE_Project/backend* 
  * run the following commad *npm server.js*
  
 * Open the Android Studio and run the process in your preferrred device.
 
## FAQ's

1. Which IP-address you are talking about?
The IP-address depends on your choice of device, which you use to run the app as the server is not yet deployed on internet you have to locally create the server and connect your device on it. If you are using the system in-built emulator use the locahost ip of your machine, in case you are using a external device first of all ensure that both the mobile and your machine are connected to the same main network then provide the ip address of your network. 

2. How to check the IP-address of the main network?
Use ifconfig command in your terminal/command prompt.

3. How to check that both are on the same network?
For checking purposes of that, we have created a **\<IP-address\>:8000/test** route, if you are able to open the link in your mobile browser, voilà you are connected!



 
 
  



### Project Done By :

![profile page](/images/1.jpeg)

- Arpit Bandejia (CS18B003)
- Dinkar (CS18B010)
- Krishnendu Sudheesh (CS18B020)
- Shreyansh Mehra (CS18B042)
