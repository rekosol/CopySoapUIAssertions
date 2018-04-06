# CopySoapUIAssertions
Extension for copying Assertions in SoapUI OS

This SoapUI extension gives the possibility to copy an assertion of a selected SOAP/HTML/REST Request teststep to another SOAP/HTML/REST Request teststep. The target teststep can be located in an arbitrary location of the workspace. It is also possible to copy to all teststeps in one selected target testcase. The location can be selected in a popup window.

## How to use this library:
* copy lib/CopyAssertion.jar file under <SOAPUI_HOME>/bin/ext
* copy div/copyAssertion-actions.xml under <SOAPUI_HOME>/bin/actions

## How copy an assertion:
In the context menu of teststeps you will find the menu point “Copy Assertions”. Selecting this menu point opens a popup window, where you can select the assertion to copy and the location of the target SOAP/HTML/REST request teststep or testcase for copying to all teststeps. 
	 
## Software is tested with: 
* SoapUI 5.4.0
* SoapUI 5.3.0 with Java 1.7