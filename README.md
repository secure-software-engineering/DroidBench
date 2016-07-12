DroidBench 3.0
===============
<p align="center">
  <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/droidbench_apps.png" width="400px"/>
</p>
DroidBench is an open test suite for evaluating the effectiveness of taint-analysis tools specifically for Android apps. 
The suite can be used to assess both static and dynamic taint analyses, but in particular it contains test cases for interesting static-analysis problems (field sensitivity, object sensitivity, tradeoffs in access-path lengths etc.) as well as for Android-specific challenges like correctly modeling an application's lifecycle, adequately handling asynchronous callbacks and interacting with the UI.

The distribution contains an eclipse workspace with all source-code projects as well as readily compiled APKs.

DroidBench was created by Christian Fritz, Steven Arzt and Siegfried Rasthofer of the [EC SPRIDE Secure Software Engineering Group](http://sse.ec-spride.de/).
Another Java-based benchmark-suite with the same aim as DroidBench is [SecuriBench](http://suif.stanford.edu/~livshits/work/securibench/index.html) which focuses on Web-based applications written in Java.

We welcome your contributions!
------------------------------
**You are most welcome to contribute additional test cases to DroidBench.** To do so, please fork the project, commit an appropriate Eclipse source project and APK, update this README and then send us a pull request.

Version 3.0-develop
====================

Version 3.0-develop comprises the following 180 test cases:

Aliasing
---------

* **FlowSensitivity1**: Sensitive data is assigned to a heap object. Only after calling the sink, an alias between the leaked object and the tainted one is created.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Merge1**: Sensitive data is assigned to a heap object which is then shuffled around. Only constant data is leaked.
* **SimpleAliasing1**: Sensitive data is assigned to a heap object and leaked through an alias.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **StrongUpdate1**: Sensitive data is assigned to a heap object, but then overwritten before it is leaked.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Arrays and Lists
----------------
* **ArrayAccess1**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are constants.
* **ArrayAccess2**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are calculated.
* **ArrayAccess3**: Sensitive data is written into an array, read back again, and leaked.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/> 
* **ArrayAccess4**: Sensitive data is written into a field of an object, this object is then stored in an array and read back again. The field that gets passed to the sink, is however, a different one 	than the one that was tainted.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ArrayAccess5**: Sensitive data is written into an array, but only the constant size of the array is leaked, not the data itself.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ArrayCopy1**: Stores a tainted value in an array and then uses System.arraycopy to copy the data to a new array which is then leaked to log.
* **ArrayToString1**: IMEI is stored in an array of String which is then converted back to String using Arrays.toString().
* **HashMapAccess1**: Stores both a tainted and an untainted value in a hash map and then leaks the untainted one. Map keys are constants.
* **ListAccess1**: Both a tainted and an untainted value are stored in a list. Only the untainted value is leaked.
* **MultidimensionalArray1**: Stores a tainted value in a 2-dimensional array and accesses it through a reference pointing to a slice containing the tainted value.

Callbacks
---------
* **AnonymousClass1**: Registers a callback handler for location updates in an anonymous inner class and leaks the incoming location data inside the callback.
* **Button1**: The sink is called after the user clicks a button. The button handler is defined via XML.
* **Button2**: Only clicking buttons in a specific order leads to a data leak.
* **Button3**: A new callback is registered in another callback's handler. The second handler leaks the data obtained by the first handler.
* **Button4**: The sink is called after the user clicks a button. The button handler is defined via XML using an include directive.
* **Button5**: IMEI is obtained during Activity's onCreate() and is later leaked through onClick's callback defined in layout's xml. Note that the first click only stores the IMEI into the button's hint. Only the second click actually leaks it.
* **LocationLeak1**: Registers a listener for location updates, stores the value and leaks it later in the lifecycle.
* **LocationLeak2**: Similar to LocationLeak1, but the activity class directly implements the callback interface.
* **LocationLeak3**: Similar to LocationLeak1, but the callback handler is in a dedicated class decoupled from the activity using an interface.
* **MethodOverride1**: Overwrites an internal Android method to hide a leak.
* **MultiHandlers1**: Contains two activities and two handlers that do not leak any data if the correct activity is used with the correct handler.
* **Ordering1**: Leaks variable contents before the handler initializing them is even registered.
* **RegisterGlobal1**: Both source and sink are part of a global (application-level) lifecycle handler.
* **Unregister1**: Registers and directly unregisters a callback before it can be invoked. The code in the callback can thus never leak any data.

Dynamic Code Loading<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
---------------------

* **CommonLibrary1**: Not a test case on its own, part of the other dynamic code loading test cases. It contains the base class for the actual class that implements source and sink.
* **DynamicBoth1**: Use dynamically loaded code to create both source and sink.
* **DynamicSink1**: Use dynamically loaded code to create a sink.
* **DynamicSource1**: Use dynamically loaded code to create a source.
* **DynamicLoadingTarget1**: The actual implementation of the source and sink class defined in CommonLibrary1.

Field and Object Sensitivity
----------------------------
* **FieldSensitivity1**: Both tainted and untainted data is stored in a data object; the untainted value is leaked.
* **FieldSensitivity2**: Similar to FieldSensitivity1, but source and sink calls are distributed across the lifecycle.
* **FieldSensitivity3**: Both tainted and untainted data is stored in a data object; the tainted value is leaked. Source and sink calls are distributed across the lifecycle.
* **FieldSensitivity4**: Field contents are sent before tainting the field.
* **InheritedObjects1**: Chooses an object's actual type based on a conditional. Only one possible type leads to a leak.
* **ObjectSensitivity1**: Writes a tainted value into an object and an untainted one into another object of the same type. Leaks the untainted value.
* **ObjectSensitivity2**: Writes a tainted value into a field and then overwrites it with untainted data.

Inter-App Communication
------------------------------------------
The first test set contains three apps. The StartActivityForResult1 app obtains the sensitive data, and sends it to the Echoer app. The Echoer sends it on to the SendSMS app where it is finally leaked.

* **Echoer**: Receives data and echoes it back to the sender.
* **SendSMS**: Reads the Device ID, passes it through Echoer, and then sends it in a text message.
* **StartActivityForResult1**: Reads the user's geographical location (via GPS), passes it through Echoer, and then writes it to a file.

The second test set contains multiple apps that obtain sensitive data and send it to the Collector app where the data is leaked.

* **Collector**: The data received through an intent is written into a file on the SD card.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **DeviceId_Broadcast1**: The device id is sent to a broadcast receiver and from there on to the collector app.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **DeviceId_ContentProvider1**: The device id is stored in a content provider and, independent from
 * the content provider, sent to the Collector app.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **DeviceId_OrderedIntent1**: The device id is obtained and sent to a broadcast receiver in the current app. There are multiple broadcast receivers with different priorities. Only the higher-priority receiver relays the data to the Collector app, the lower-priority receiver only shows the data to the user (no leak).<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **DeviceId_Service1**: This app starts a service which sends the device id to the Collector app where it is leaked.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

* **Location1**: This app obtains the location data and sends it to the Collector app.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Location_Broadcast1**: This app obtains the location data, and sends it to a broadcast
 * receiver in the same app. This broadcast receiver then sends the data to the
 * Collector app.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Inter-Component Communication
------------------------------
* **ActivityCommunication1**: Contains two activities that communicate using static fields.
* **ActivityCommunication2**: IMEI is obtained in one Activity and stored in an Intent's extra for starting the next Activity which will then leak IMEI to log.
* **ActivityCommunication3**: Another Activity is started using class name.  IEMI is leaked in the Activity to be started.
* **ActivityCommunication4**: An Intent for starting another Activity is created by concatenating two constant strings.  The Activity to be started leaks the IMEI which was passed in as the Intent's extra.   
* **ActivityCommunication5**: A Activity is started using constant component's Name and Intent.setComponent. The started Activity leaks the IMEI to log.
* **ActivityCommunication6**: An Intent to start another Activity is passed through LinkedList's add() and retrieved with LinkedList.get().  The retrieved Intent is used to start a leaking Activity.
* **ActivityCommunication7**: An Activity class is created and its classname is retrieved using getClass() on the instance object.  The classname is then used to start the second Activity which then leaks IMEI information form Intent's extra.
* **ActivityCommunication8**: An action for the Intent to start another Activity is passed through and retrieved with LinkedList.get().  The retrieved String is used to start the leaking Activity.
* **BroadcastTaintAndLeak1**:  IMEI is obtained in an Activity and stored in the Intent's extra of the Intent to the BroadcastReceiver which was registered programmatically.  The Receiver leaks the IMEI info.
* **ComponentNotInManifest1**: IMEI is obtained in one Activity and is stored in the Intent's to start a leaking Activity which is not in the AndroidManifest.xml.  No leak happens as it is not valid.
* **EventOrdering1**: IMEI is obtained and stored in the SharedPreferences of the secondary Activity (InFlowActivity).  The first Activity (OutFlowActivity) starts the second Activity.  The second-time the 2nd Activity is started, IMEI is leaked to log.
* **IntentSink1**: A tainted value is leaked as the result of an activity.
* **IntentSink2**: Similar to IntentSink, but the value is sent out in a callback method defined in XML.
* **IntentSource1**: Two tainted leaks: the first one is that a tainted value is leaked to another app using an intent by startActivityForResult. The other one is that onActivityResult method gets intent as tainted data and then logs it.
* **ServiceCommunication1**: IMEI is obtained and sent to a Service which then leaks the info in the Messenger's Handler.  
* **SharedPreferences1**:  IMEI is obtained in the one Activity and stored in the SharedPreferences which is leaked by another Activity.
* **Singletons1**: IMEI is stored in a singleton object in one Activity and leaked in a different Activity.
* **UnresolvableIntent1**:  The app tries to start an Activity int an Intent that cannot be resolved statically.  One of the two Activities may be started based on a random boolean, and both of them leak IMEI from the starting Activity to log.

Lifecycle
---------
* **ActivityEventSequence1**: Source in Activity.onCreate(), sink in Activity.onResume().<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ActivityEventSequence2**: Checks whether the analysis tool correctly handles events.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ActivityEventSequence3**: Saves and restores state using the Activity's state callbacks.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ActivityLifecycle1**: Calls to sources and sinks distributed across an activity lifecycle.
* **ActivityLifecycle2**: Activity class inherited from a superclass containing the lifecycle method which leaks the tainted value.
* **ActivityLifecycle3**: Calls to sources and sinks distributed across instance state handling methods.
* **ActivityLifecycle4**: A tainted value is obtained on `onPause()` and leaked when the activity is restarted later.
* **ActivitySavedState1**: IMEI is saved during onSaveInstanceState() via bundle's persistence and leaked during the next onCreate()
* **ApplicationLifecycle1**:  Obtains a secret value when the application is launched and sends it out in the onResume() method of the main activity.
* **ApplicationLifecycle2**:  Obtains a secret value when the application is launched and sends it out in the low memory callback of the application.
* **ApplicationLifecycle3**:  Obtains a secret value when a content provider is initialized and leaks it in the onCreate() method of the application.
* **AsynchronousEventOrdering1**: Obtains IMEI during onResume() and leaks it during onStop() with an overwrite in onLowMemory().
* **BroadcastReceiverLifecycle1**: Calls to sources and sinks distributed across a broadcast receiver lifecycle.
* **BroadcastReceiverLifecycle2**: The sensitive data is read in onCreate() and sent out in a dynamically registered broadcast receiver.
* **BroadcastReceiverLifecycle3**: The sensitive data is read and leaked in a dynamically registered broadcast receiver.
* **EventOrdering1**: IMEI is obtained the first time onLowMemory is called, and is leaked the second time onLowMemory is called, but only of no onContentChanged() occurred in between.
* **FragmentLifecycle1**: Calls to sources and sinks distributed across a fragment lifecycle.
* **ServiceEventLifecycle1**: Obtains the IMEI in onStartCommand(), copies it in onBind(), and leaks it in onStartCommand().<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ServiceEventLifecycle2**: Like ServiceEventLifecycle1, but performs a second copy stop in OnUnbind().<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ServiceEventLifecycle3**: Obtains the IMEI in onStartCommand(), copies it in onBind(), and leaks it in onUnbind().<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ServiceLifecycle2**: IMEI is obtained at the end of onStartCommand and is stored to a service's field.  It is leaked the second time the service command starts.
* **SharedPreferenceChanged1**: onCreate(), IMEI is put into the SharedPreferences and it triggers onSharedPreferenceChanged() which then leaks the IMEI to Android Log.

General Java
------------
* **Clone1**: IMEI is added to a linkedlist, and the list is cloned to a new list, then the tainted member of the newlist is leaked.
* **DirectBuffer**: Test case for under/over tainted ByteBuffers.
* **Exceptions1**: Saves a tainted value into a local variable, raises an exception and sends the value out in the exception handler.
* **Exceptions2**: Saves a tainted value into a local variable, implicitly raises an exception (ArrayIndexOutOfBounds) and sends the data out in the exception handler.
* **Exceptions3**: Saves a tainted value into a local variable, but the exception handler which would send it out is never invoked.
* **Exceptions4**: Throws an exception containing a tainted value and sends it out in the exception handler.
* **Exceptions5**: Throws an exception containing tainted data inside a called method and catches it in the caller.
* **Exceptions6**: Throws an implicit exception inside a called method and catches and leaks data in the catch block inside the caller.
* **Exceptions7**: A leak happens inside an exception handler that can never be reached, because it
handles an exception type that is never thrown.
* **FactoryMethods1**: Obtains a LocationManager from a factory method contained in the Android operating system, reads out the location, and leaks it.
* **Loop1**: Contains a simple loop and a data leak.
* **Loop2**: Retrieves location information through a callback and leaks it via nested loops.
* **Serialization1**:  IMEI is obtained and serialized/deserialzed throught ObjectOutputStream and ObjectInputStream.
* **SourceCodeSpecific1**: Uses unusual code construct `a = p ? b : c.`
* **StartProcessWithSecret1**: IMEI is obtained and leaked through ProcessBuilder's start().  
* **StaticInitialization1**: Passes a tainted value into a static initialization method.
* **StaticInitialization2**: Sensitive data is obtained during static initialization of a class and leaked in non-static code
* **StaticInitialization3**: IMEI is obtained during static initializer and is stored and overriding in the Activity's field which was not tainted at the beginning.
* **StringFormatter1**: IMEI is passed to a formatter and then the formatted buffer is converted back to String before leaked to log.
* **StringPatternMatching1**: IMEI is matched against .* regular expression, and the matched group is leaked.
* **StringToCharArray1**: IMEI is obtained and stored in the char[]  which is then later converted to String before leaked to log.
* **StringToOutputStream1**: IMEI is obtained and passed through ByteArrayOutputStream as String bytes back to String.
* **UnreachableCode**: Passes tainted data into a method that is never called.
* **VirtualDispatch1**: Depending on a click counter, one class or another is instantiated. However, only one of the classes actually leaks data, the only ever leaks a constant string.
* **VirtualDispatch2**: A method in the base class returns untainted information, the same method in one of the derived classes returns sensitive (IMEI) information.  That information is later leaked through SMS.
* **VirtualDispatch3**: Two classes implement an interface, but only one of them returns sensitive data. The leak however happens on the other implementation that only returns constant data.
* **VirtualDispatch4**: Similar to VirtualDispatch3, but with slightly more type information.

Miscellaneous Android-Specific
------------------------------
* **ApplicationModeling1**: Stores IMEI in Application's object and later leaks it in a different Activity.
* **DirectLeak1**: The device id is read out and sent via SMS on the activity's `onCreate()` event.
* **InactiveActivity**: Data leak in a disabled activity.
* **LogNoLeak**: Writes untainted data into a log file.
* **Obfuscation1**: This APK contains an own implementation of android.telephony.TelephonyManager. However, on a real device the preloaded OS implementation will always hide the custom one and you will always get a real IMEI. Testes on Galaxy Nexus 4, no guarantees for the emulator, though.
* **Library1**: not a test case on its own, part of Library2.
* **Library2**: The IMEI is read out inside a custom library and then leaked in the app.
* **PrivateDataLeak1**: Summary test case containing various challenges.
* **PrivateDataLeak2**: Leaks a value from a password field.
* **PrivateDataLeak3**: The IMEI is written into a file, read out again and then leaked.
* **PublicAPIField1**: IMEI is obtained and converted to 2 floating point numbers as x and y of PointF.  Value of PointF is leaked.
* **PublicAPIField2**:  IMEI is retrieved and stored to Intent.action as a data holder.
* **View1**: The IMEI is leaked inside the draw event of a custom view. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Implicit Flows
--------------
* **ImplicitFlow1**: A value from a source gets obfuscated by two different ways and is then written to the log.
* **ImplicitFlow2**: Based on an input of a password field a log message is written.
* **ImplicitFlow3**: This test cases checks the type of the object to determine which information to write to the log.
* **ImplicitFlow4**: Several implicit flows exist in this test case.
* **ImplicitFlow5**: Implicit control flow through exceptions. Only if the value is smaller than 43, an exception is thrown and then a leak happens.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ImplicitFlow6**: Implicit control flow, but leaked value is the same regardless of the sensitive value.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Reflection
----------
* **Reflection1**: Sensitive data is stored in a field of a reflective class and directly read out again and leaked.
* **Reflection2**: Sensitive data is stored in a field of a reflective class, read out again using a method implemented in the "unknown" class and leaked.
* **Reflection3**: Sensitive data is stored using a setter in a reflective class, read back using a getter and then leaked. No type information on the target class is used.
* **Reflection4**: Sensitive data is read using a function in a reflective class and leaked using another function in the same reflective class.
* **Reflection5**: Sensitive data is leaked using reflective invocation without using "newInstance()" to create a class instance. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Reflection6**: Sensitive data is read using reflective invocation without using "newInstance()" to create a class instance. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Reflection7**: Use a unusual way to get the class name for reflection calls. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Reflection8**: The reflective class has two methods with the same name. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Reflection9**: Names of reflective methods and parameters are not constant strings. <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Reflection_ICC <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
---------------

* **ActivityCommunication2**: Data is obtained and sent through an implicit intent The intent is reflected. Source is getDeviceId and sink is Log.
* **AllReflection**: Source, Sink, Intent(Explicit) all are reflected. Sink is SMS.
* **OnlyIntent**: Intent itself is reflected during send. Intent is Explicit. Sink is SMS.
* **OnlyIntentReceive**: Intent Send is not reflected only its receive is reflected by passing intent as object. Intent is Explicit Intent.
* **OnlySMS**: Data is obtained and sent to Activity2 where it is leaked. Sink is reflected. Sink is SMS. 
* **OnlyTelephony**: Source is getDeviceid and Reflection is used to call this Source API. The data is then transmitted to a second activity. Sink is in the second activity and is SMS. Sink and Intent are not Reflected.
* **OnlyTelephony_Dynamic**: Source API is getDeviceId. The API is called using reflection along with concatenation to generate the string at run time. The data is then transmitted to Activity2 where it is leaked.
* **OnlyTelephony_Reverse**: Source API is getDeviceId which is obtained using reverse() function. The API is called using reflection. The data is then passed on to a second activity where it is leaked.
* **OnlyTelephony_Substring**: Source API is getDeviceId which is obtained using substring() function. The API is called using reflection. The data is then passed on to a second activity where it is leaked.
* **SharedPreferences1**: Reflection in use of sharedpreference is done. Sink is Log

Self-Modification<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
------------------

* **BytecodeTamper1**: This test case uses native code to change the target of the sink method invocation. The new target actually leaks the sensitive data, whereas the original one just leaked a constant string.
* **BytecodeTamper2**: This test case uses native code to change the target of the source method invocation. The new target actually returned the sensitive data, whereas the original one just returned a constant string.
* **BytecodeTamper3**: This test case uses native code to change the target of both the source and the sink method invocations. The new target actually returns/leaks the sensitive data, whereas the original one just returned/leaked a constant string.
* **BytecodeTamper4**: Contains self-modified code, but no source or sink is involved.

Threading
----------

* **AsyncTask1**: Sensitive data is read in onCreate() and send out in a dedicated thread started using Android's AsyncTask mechanism.
* **JavaThread1**: Sensitive data is read in onCreate() and send out in a dedicated thread started using Java's normal threading mechanism.
* **JavaThread2**: Sensitive data is read in onCreate() and send out in a dedicated thread started using Java's Runnable mechanism.
* **Executor1**: Sensitive data is read in onCreate() and send out in a dedicated thread started using Java's Executor mechanism.
* **Looper1**: Sensitive data is read in onCreate() and enqueued for a custom thread hosting an Android Looper whose handler sends out the data.
* **TimerTask1**: Sensitive data is read in onCreate() and enqeued to be sent out 2 seconds later using Java's TimerTask architecture.

Emulator Detection
--------------------

* **Battery1**: This test detects the Android emulator by checking the battery status. For emulator the battery status is always 50% or 0. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Bluetooth1**: This test detects the Android emulator by checking the bluetooth. The non-presence of Bluetooth sensor identify the environment as Emulator. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Build1**: This test detects the Android emulator by checking the various Build properties like SDK, Board, Brand etc. This app send IMEI number via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Contacts1**: This test detects the Android emulator by checking the number of contacts and calllogs both. Below value of 5 for both identify the environment as Emulator. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **ContentProvider1**: This test case detects the Android emulator by checking the IMEI in a content provider. The IMEI is only sent via SMS in the activity if the app runs on a real phone.
* **DeviceId1**: This test detects the Android emulator by checking the IMEI number using getDeviceId API. IMEI value of 16 0's identify environment as Emulator. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **File1**: This test detects the Android emulator by checking the system files. Some files are specific to emulator while others are to device. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **IMEI1**: This test case detects the Android emulator by truncating the secret data which is leaked at a position computed from the IMEI. On an emulator, the IMEI is expected to be 00..0.
* **IP1**: This test detects the Android emulator by checking the IP Address of environment. A value of 10.0.2.15 is the identification of Emulator. This app send IMEI number via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **PI1**: This test detects the Android emulator by doing a large computation i.e calculating value of pi till n decimal places. A threshhold value is obtained by doing experiments on 100 devices and emulators. Based on this threshhold value, a decision is taken. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **PlayStore1**: This test case detects the Android emulator by whether the Play Store app is installed on the phone. The IMEI is only sent via SMS if the app runs on a real phone.
* **PlayStore2**: This test detects the Android emulator by checking absense of Google Play Services. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **Sensors1**: This test detects the Android emulator by counting the distinct sensors. A total of 13 different type of sensors is checked. A emulator will always have 7 or less sensors. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **SubscriberId1**: This test detects the Android emulator by checking Subscriber Id which always start with 310260000000000 for emulators. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
* **VoiceMail1**: This test detects the Android emulator by checking VoiceMail number. A VoiceMail number with value +15552175049, identify environment as emulator. This app send IMEI number  via SMS if the app runs on a real phone.<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>

Native Code<img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
------------

* **JavaIDFunction**: This test case sends tainted data from Java to Native to Java and back to native where it is leaked.
* **NativeIDFunction**: This test case sends tainted data from Java to Native and back to Java where it is leaked.
* **SinkInNativeCode**: This test case obtains the IMEI in Java code and leaks it in native code by calling back to the Java-based Android API.
* **SinkInNativeLibCode**: This test case obtains the IMEI in Java code and leaks it in native code using Linux sockets.
* **SourceInNativeCode**: This test case obtains the IMEI in native code and leaks it in Java code.

Unreachable Code <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
--------------------

* **UnreachableBoth1**: Both source and sink are in unreachable branches.
* **UnreachableSink1**: Sensitive data is leaked in a branch of "switch" instruction, which will never be executed.
* **UnreachableSource1**: Sensitive data is read in a branch of "if" instruction, which will never be executed.

Dynamic Loading <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
--------------------

* **CommonLibrary1**: This is not a test case on its own. It is a shared library between app and its dynamically loaded code.
* **DynamicLoadingTarget1**: This is not a test case on its own. It is a library which will be dynamically loaded by other test cases.
* **DynamicSource1**: Sensitive data is read by code in a dynamically loaded class.
* **DynamicSink1**: Sensitive data is leaked by code in a dynamically loaded class.
* **DynamicBoth1**: Both source and sink are in a dynamically loaded class.

Self-Modification <img src="https://raw.github.com/secure-software-engineering/DroidBench/develop/new.gif"/>
Notice: Different cpu architecture and different android versions lead to different memory layout. The samples in this category works on arm64_v8a, Android 6.0. Same goal could be achieved in other architecture and Android versions by slightly modify the source code of the samples.
--------------------

* **BytecodeTamper1-4**: Use native code to modify the bytecode in runtime, and create sources and sinks.

Acknowledgements
=================

We would like to thank, among others, the following organizations which contributed
test cases to DroidBench:

* 40 apps were contributed by **The DroidSafe Team at MIT** http://mit-pac.github.io/droidsafe-src/
* Some inter-component and inter-application test cases were contributed by Li Li from the **University of Luxembourg**
* 6 apps for checking event handling were provided by the **University of Texas**
* 10 apps for inter-component communication in combination with reflections were provided by the **Malviya National Institute of Technology, Jaipur (INDIA)**.
* 12 apps for emulator detection were provided by the **Malviya National Institute of Technology, Jaipur (INDIA)** under DeITy Project funded from Government of India.
* 8 apps for inter-app data flow tracking were provided by the **Malviya National Institute of Technology, Jaipur (INDIA)** under DeITy Project funded from Government of India.
* 15 apps for dynamic code loading, reflective method calls, self-modifying code, and unreachable code  were provided by the Wayne State University.