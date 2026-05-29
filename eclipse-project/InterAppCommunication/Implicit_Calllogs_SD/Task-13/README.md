## Aim : To make an application "x" that reads call logs through service and pass it to  application "y" thaat writes it in sd card thorugh Broadcastreceiver.
__Flow : Application Twin10 offers a Main activity.The activity starts a service which read call logs and store them in stringbuffer. The service calls broadcast receiver of application ReceiverEx which gets the data of stringbuffer throught intent. This receiver now writes the data of call log in the sd card.__

Intent- Service Calling receiver implicitly.
