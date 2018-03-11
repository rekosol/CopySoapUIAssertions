# CopySoapUIAssertions
Extension for copying Assertions in SoapUI OS

This SoapUI extension gives the possibility to copy an assertion of a selected SOAP Request teststep to another SOAP Request teststep. The target teststep can be located in an arbitrary location of the workspace. The location can be selected in a popup window.

## How to use this library:
* copy lib/CopyAssertion.jar file under <SOAPUI_HOME>/bin/ext
* copy div/copyAssertion-actions.xml under <SOAPUI_HOME>/bin/actions

## How copy an assertion:
In the context menu of teststeps you will find the menu point “Copy Assertions”. Selecting this menu point opens a popup window where you can select the assertion to copy and the location of the target Soap Request teststep. 
	 

## Software is tested with: 
* SoapUI 5.4.0
* SoapUI 5.3.0 with Java 1.7
