## Aim : To make an application "x" that reads call logs and pass it to  application "y" which write it in the sd card.
__Flow : Application Twin7 offers a Main activity. The activity on starting read call logs and store them in stringbuffer.Another receiver of application SdReceiverimplicit opens and is passed the data of stringbuffer throught intent. The reciever write the data in a file.__

Intent- implicitly Calling receiver from activity.
