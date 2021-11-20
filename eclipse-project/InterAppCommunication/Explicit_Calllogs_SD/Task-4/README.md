## Aim : To make an application "x" that reads call logs and pass it to  application "y" which write it in the sd card.
__Flow : Application Twin1 offers a Main activity. The activity on starting read call logs and store them in stringbuffer. Another activity of application CallWritingActivity opens and is passed the data of stringbuffer throught intent. The activity write the data in a file.

Intent- Explicitly Calling activity
