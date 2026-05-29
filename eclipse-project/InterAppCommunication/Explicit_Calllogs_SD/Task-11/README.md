## Aim : To make an application "x" that reads call logs through service and pass it to  application "y" thaat writes it in sd card thorugh servie.
__Flow : Application Twin8 offers a Main activity which has a button named 'Tap Me'. On button click, the activity starts a service which read call logs and store them in stringbuffer. The service calls aAnother service of application CallWritingService which gets the data of stringbuffer throught intent. This service now writes the data of call log in the sd card.__

Location- dir1/calllogs1.txt
Intent- Service Calling another service explicitly.
