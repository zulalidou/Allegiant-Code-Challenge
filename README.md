# Allegiant-Code-Challenge

TOOLS NEEDED
-------------

1.) Have Java installed

	a. Make sure you have Java downloaded. If you don't, please go to the oracle website,
	   and look for the Java SE Development Kit for whichever OS you're using.


2.) Have Selenium installed

	a. If you don't have Selenium already installed, go to their website and install the latest version (3.141.59), which is a jar file.
	   Link: https://selenium.dev/downloads/

	b. Scroll down to the "Selenium Client & WebDriver Language Bindings" and download the Java zip folder (also version 3.141.59).


3.) Have the Chrome Driver installed

	a. Go to https://chromedriver.chromium.org/, and install the latest version (79.0.3945.36) of the chrome driver.



======================================================================================================================================================

BEFORE RUNNING THE PROJECT

1.) Create a new Java project folder. Create a new package, which will contain the class that tests the allegiant website.


2.) Add the Selenium jar file and zip folder (downloaded from the "TOOLS NEEDED" section) to the build path of the project folder


3.) Create a driver folder within the Java project folder which will store the chrome driver (downloaded from the "TOOLS NEEDED" section).


4.) Make sure the Java project has assertions enabled.



======================================================================================================================================================

RUN THE PROJECT

- After both sections about have been completed, you can now run the project.

- Outcomes
	a.) If the total price matches the prices of all selected items, the webdriver will be closed and a message will be displayed in the compiler's
	    console.

	b.) If the total price does NOT match the prices of all selected items, an AssertionError will occur (which will be displayed in the compiler's
	    console),  and the program will stop running.
