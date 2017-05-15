## Aim : To make an application "x" that reads call logs through receiver and pass it to  application "y" thaat writes it in sd card through receiver.
__Flow : Application Task18 offers a Main activity which has a button named 'Tap Me'. On button click, the activity starts a receiver which read call logs and store them in stringbuffer. The receiver starts receiver of application RecdiverSdEx which gets the data of stringbuffer throught intent. This receiver now writes the data of call log in the sd card.__

Location- dir1/callbroadcast4.txt
Intent- Receiver calling receiver explicitly.
