DroidBench
==========
<p align="center">
  <img src="http://sseblog.ec-spride.de/wp-content/uploads/2013/05/droidbench_apps-300x172.png" width="400px"/>
</p>
DroidBench is an open test suite for evaluating the effectiveness of taint-analysis tools specifically for Android apps. 
The suite can be used to assess both static and dynamic taint analyses, but in particular it contains test cases for interesting static-analysis problems (field sensitivity, object sensitivity, tradeoffs in access-path lengths etc.) as well as for Android-specific challenges like correctly modeling an application’s lifecycle, adequately handling asynchronous callbacks and interacting with the UI.

The distribution contains an eclipse workspace with all source-code projects as well as readily compiled APKs.

DroidBench was created by Christian Fritz, Steven Arzt and Siegfried Rasthofer of the [EC SPRIDE Secure Software Engineering Group](http://sse.ec-spride.de/).
Another Java-based benchmark-suite with the same aim as DroidBench is [SecuriBench](http://suif.stanford.edu/~livshits/work/securibench/index.html) which focuses on Web-based applications written in Java.

We welcome your contributions!
------------------------------
**You are most welcome to contribute additional test cases to DroidBench.** To do so, please fork the project, commit an appropriate Eclipse source project and APK, update this README and then send us a pull request.

Version 1.2
===========
Version 1.2 comprises the following 64 test cases:

Arrays and Lists
----------------
* **ArrayCopy**: Stores a tainted value in an array and then uses System.arraycopy to copy the data to a new array which is then leaked to log.
* **ArraySlice**: Stores a tainted value in a 2-dimensional array and accesses it through a reference pointing to a slice containing the tainted value.
* **ArrayAccess1**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are constants.
* **ArrayAccess2**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are calculated.
* **HashMapAccess1**: Stores both a tainted and an untainted value in a hash map and then leaks the untainted one. Map keys are constants.
* **ListAccess1**: Both a tainted and an untainted value are stored in a list. Only the untainted value is leaked.

Callbacks
---------
* **AnonymousClass1**: Registers a callback handler for location updates in an anonymous inner class and leaks the incoming location data inside the callback.
* **Button1**: The sink is called after the user clicks a button. The button handler is defined via XML.
* **Button2**: Only clicking buttons in a specific order leads to a data leak.
* **Button3**: A new callback is registered in another callback's handler. The second handler leaks the data obtained by the first handler.
* **Button4**: The sink is called after the user clicks a button. The button handler is defined via XML using an include directive.
* **LocationLeak1**: Registers a listener for location updates, stores the value and leaks it later in the lifecycle.
* **LocationLeak2**: Similar to LocationLeak1, but the activity class directly implements the callback interface.
* **LocationLeak3**: Similar to LocationLeak1, but the callback handler is in a dedicated class decoupled from the activity using an interface.
* **MethodOverride1**: Overwrites an internal Android method to hide a leak.
* **MultiHandlers1**: Contains two activities and two handlers that do not leak any data if the correct activity is used with the correct handler.
* **Ordering1**: Leaks variable contents before the handler initializing them is even registered.
* **RegisterGlobal1**: Both source and sink are part of a global (application-level) lifecycle handler.
* **Unregister1**: Registers and directly unregisters a callback before it can be invoked. The code in the callback can thus never leak any data.

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
-----------------------
* **IntentSink1**: A tainted value is leaked to another application using an intent.
* **IntentSink2**: Similar to IntentSink, but the value is sent out in a callback method defined in XML.
* **ActivityCommunication1**: Contains two activities that communicate using static fields.
* **IntentSource1**: Two tainted leaks: the first one is that a tainted value is leaked to another app using an intent by startActivityForResult. The other one is that onActivityResult method gets intent as tainted data and then logs it.
*
* **ICC-Action-String-Operations**: IMEI is obtained in one Activity and stored in an Intent's extra for starting the next Activity which will then leak IMEI to log.
* **ICC-Broadcast-Programmatic-IntentFilter**:  IMEI is obtained in an Activity and stored in the Intent's extra of the Intent to the BroadcastReceiver which was registered programmatically.  The Receiver leaks the IMEI info.
* **ICC-Component-Not-In-Manifest**: IMEI is obtained in one Activity and is stored in the Intent's to start a leaking Activity which is not in the AndroidManifest.xml.  No leak happens as it is not valid.
* **ICC-ComponentName-Class-Constant**: Another Activity is started using class name.  IEMI is leaked in the Activity to be started.
* **ICC-Concat-Action-String": An Intent for starting another Activity is created by concatenating two constant strings.  The Activity to be started leaks the IMEI which was passed in as the Intent's extra.   
* **ICC-Event-Ordering**: IMEI is obtained and stored in the SharedPreferences of the secondary Activity (InFlowActivity).  The first Activity (OutFlowActivity) starts the second Activity.  The second-time the 2nd Activity is started, IMEI is leaked to log.
* **ICC-Intent-ComponentName**: A Activity is started using constant component's Name and Intent.setComponent.  The started Activity leaks the IMEI to log.
* **ICC-Intent-Passed-Through-API**: An Intent to start another Activity is passed through LinkedList's add() and retreived with LinkedList.get().  The retrieved Intent is used to start a leaking Activity.
* **ICC-Non-Constant-Class-Object**: An Activity class is created and its classname is retrieved using getClass() on the instance object.  The classname is then used to start the second Activity which then leaks IMEI information form Intent's extra.
* **ICC-Pass-Action-Through-API**: An action for the Intent to start another Activity is passed through and retreived with LinkedList.get().  The retrieved String is used to start the leaking Activity.
* **ICC-Service-Messages**: IMEI is obtained and sent to a Service which then leaks the info in the Messenger's Handler.  
* **ICC-Unresolvable-Intent**:  The app tries to start an Activity int an Intent that cannot be resolved statically.  One of the two Activities may be started based on a random boolean, and both of them leak IMEI from the starting Activity to log.   

Lifecycle
---------
* **ActivityLifecycle1**: Calls to sources and sinks distributed across an activity lifecycle.
* **ActivityLifecycle2**: Activity class inherited from a superclass containing the lifecycle method which leaks the tainted value.
* **ActivityLifecycle3**: Calls to sources and sinks distributed across instance state handling methods.
* **ActivityLifecycle4**: A tainted value is obtained on `onPause()` and leaked when the activity is restarted later.
* **ApplicationLifecycle1**:  Obtains a secret value when the application is launched and sends it out in the onResume() method of the main activity.
* **ApplicationLifecycle2**:  Obtains a secret value when the application is launched and sends it out in the low memory callback of the application.
* **ApplicationLifecycle3**:  Obtains a secret value when a content provider is initialized and leaks it in the onCreate() method of the application.
* **BroadcastReceiverLifecycle1**: Calls to sources and sinks distributed across a broadcast receiver lifecycle. 
* **FragmentLifecycle1**: Calls to sources and sinks distributed across a fragment lifecycle.
* **ServiceLifecycle1**: Calls to sources and sinks distributed across a service lifecycle.
*
* **Activity-Asynchronous-Event-Ordering**: obtains IMEI during onResume() and leaks it durin onStop().
* **Activity-Saved-State**: IMEI is saved during onSaveInstanceState() via bundle's persistence and leaked during the next onCreate()
* **Application-Modeling**: Stores IMEI in Application's object and later leaks it in a different Activity.
* **Button-Object-Allocation**: IMEI is obtained during Activity's onCreate() and is later leaked through onClick's callback defined in layout's xml.
* **Event-Context-Shared-Pref-Listener**: onCreate(), IMEI is put into the SharedPreferences and it triggers onSharePreferenceChanged() which then leaks the IMEI to Android Log.
* **Event-Ordering**: IMEI is obtained the first time onLowMemory is called, and is leaked the secondtime onLowMemory is called.
* **Fragments**:  IMEI is obtained from one Fragment's click callback and is leaked through another Fragment's callback.
* **Service-Lifecycle**: IMEI is obtained at the end of onStartCommand and is stored to a service's field.  It is leaked the second time the service command starts.

General Java
------------
* **Exceptions1**: Saves a tainted value into a local variable, raises an exception and sends the value out in the exception handler.
* **Exceptions2**: Saves a tainted value into a local variable, implicitly raises an exception (ArrayIndexOutOfBounds) and sends the data out in the exception handler.
* **Exceptions3**: Saves a tainted value into a local variable, but the exception handler which would send it out is never invoked.
* **Exceptions4**: Throws and exception containing a tainted value and sends it out in the exception handler.
* **Loop1**: Contains a simple loop and a data leak.
* **Loop2**: Retrieves location information through a callback and leaks it via nested loops.
* **SourceCodeSpecific1**: Uses unusual code construct `a = p ? b : c.`
* **StaticInitialization1**: Passes a tainted value into a static initialization method.
* **StaticInitialization2**: Sensitive data is obtained during static initialization of a class and leaked in non-static code
* **UnreachableCode**: Passes tainted data into a method that is never called.
*
* **Clinit**: IMEI is obtained during static initializer and is stored and overriding in the Activity's field which was not tainted at the beginning.
* **Clone**: IMEI is added to a linkedlist, and the list is cloned to a new list, then the tainted member of the newlist is leaked.
* **Dynamic-Dispatch**: A method in the base class returns untainted information, the same method in one of the derived classes returns sensitive (IMEI) information.  That information is later leaked through SMS.
* **NonSink-Argument-Flow**: IMEI is obtained and leaked through ProcessBuilder's start().  
* **OutputStream**: IMEI is obtained and passed through ByteArrayOutputStream as String bytes back to String.
* **Public-API-Field**: IMEI is obtained and converted to 2 floating point numbers as x and y of PointF.  Value of PointF is leaked.
* **Serialization**:  IMEI is obtained and serialized/deserialzed throught ObjectOutputStream and ObjectInputStream.
* **Pattern-Matcher**: IMEI is matched against .* regular expression, and the matched group is leaked.
* **String-To-Char**: IMEI is obtained and stored in the char[]  which is then later converted to String before leaked to log.
* **String-Formater**: IMEI is passed to a formatter and then the formatted buffer is converted back to String before leaked to log.
* **ToString**: IMEI is stored in an array of String which is then converted back to String using Arrays.toString().

Miscellaneous Android-Specific
------------------------------
* **DirectLeak1**: The device id is read out and sent via SMS on the activity's `onCreate()` event.
* **InactiveActivity**: Data leak in a disabled activity.
* **LogNoLeak**: Writes untainted data into a log file.
* **Obfuscation1**: This APK contains an own implementation of android.telephony.TelephonyManager. However, on a real device the preloaded OS implementation will always hide the custom one and you will always get a real IMEI. Testes on Galaxy Nexus 4, no guarantees for the emulator, though.
* **Library1**: not a test case on its own, part of Library2.
* **Library2**: The IMEI is read out inside a custom library and then leaked in the app.
* **PrivateDataLeak1**: Summary test case containing various challenges.
* **PrivateDataLeak2**: Leaks a value from a password field.
* **PrivateDataLeak3**: The IMEI is written into a file, read out again and then leaked.
*
* **Intent-Class-Modeling**:  IMEI is retrieved and stored to Intent.action as a data holder. 
* **Parcel**: IEMI is marshalled and unmarshalled from a Parcel and then leaked to SMS.
* **SharedPreferences:  IMEI is obtained in the one Activity and stored in the SharedPreferences which is leaked by another Activity.
* **Two-Components-Shared-Memory**: IMEI is stored in a singleton object in one Activity and leaked in a different Activity.

Implicit Flows
--------------
* **ImplicitFlow1-4**: Test cases for implicit flows.

Reflection
----------
* **Reflection1**: Sensitive data is stored in a field of a reflective class and directly read out again and leaked.
* **Reflection2**: Sensitive data is stored in a field of a reflective class, read out again using a method implemented in the "unknown" class and leaked.
* **Reflection3**: Sensitive data is stored using a setter in a reflective class, read back using a getter and then leaked. No type information on the target class is used.
* **Reflection4**: Sensitive data is read using a function in a reflective class and leaked using another function in the same reflective class.
