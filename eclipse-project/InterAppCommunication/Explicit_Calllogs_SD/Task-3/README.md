## Aim : To make an application "x" that reads call logs and pass it to service in application "y" which write it in the sd card.
__Flow : Application twinit offers a Main activity which has a button named 'Tap Me'. The activity on starting read call logs and store them in stringbuffer. On Button click, A serice named 'CallWritingSerive'starts in background and is passed the data of stringbuffer throught intent. The serice CallWritingService writes the data in a file named  'calllogs.txt' in a directory named 'dir1'.__

In devices with multiple shared/external storage directories, this directory represents the primary storage that the user will interact with.Traditionally this is in SD card, but it may also be implemented as built-in storage in a device that is distinct from the protected internal storage and can be mounted as a filesystem on a computer. [Android Documentation](https://developer.android.com/reference/android/os/Environment.html)

Intent- Explicitly calling service from activity.
Location- dir1/calllogs.txt
