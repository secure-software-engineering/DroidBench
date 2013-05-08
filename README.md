DroidBench
==========
DroidBench is an open test suite for evaluating the effectiveness of taint-analysis tools specifically for Android apps. 
The suite can be used to assess both static and dynamic taint analyses, but in particular it contains test cases for interesting static-analysis problems (field sensitivity, object sensitivity, tradeoffs in access-path lengths etc.) as well as for Android-specific challenges like correctly modeling an application’s lifecycle, adequately handling asynchronous callbacks and interacting with the UI.

The distribution contains an eclipse workspace with all source-code projects as well as readily compiled APKs.

DroidBench was created by Christian Fritz, Steven Arzt and Siegfried Rasthofer of the [EC SPRIDE Secure Software Engineering Group](http://sse.ec-spride.de/).

We welcome your contributions!
------------------------------
**You are most welcome to contribute additional test cases to DroidBench.** To do so, please fork the project, commit an appropriate Eclipse source project and APK, update this README and then send us a pull request.

Version 1.0
===========
Version 1.0 comprises the following 39 test cases:

Arrays and Lists
----------------
* **ArrayAccess1**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are constants.
* **ArrayAccess2**: Stores both a tainted and an untainted value in an array and then leaks the untainted one. Array indices are calculated.
* **ListAccess1**: Both a tainted and an untainted value are stored in a list. Only the untainted value is leaked.

Callbacks
---------
* **AnonymousClass1**: Registers a callback handler for location updates in an anonymous inner class and leaks the incoming location data inside the callback.
* **Button1**: The sink is called after the user clicks a button. The button handler is defined via XML.
* **Button2**: Only clicking buttons in a specific order leads to a data leak.
* **LocationLeak1**: Registers a listener for location updates, stores the value and leaks it later in the lifecycle.
* **LocationLeak2**: Similar to LocationLeak1, but the activity class directly implements the callback interface.
* **MethodOverride1**: Overwrites an internal Android method to hide a leak.

Field and Object Sensitivity
----------------------------
* **FieldSensitivity1**: Both tainted and untainted data is stored in a data object; the untainted value is leaked.
* **FieldSensitivity2**: Similar to FieldSensitivity1, but source and sink calls are dis- tributed across the lifecycle.
* **FieldSensitivity3**: Both tainted and untainted data is stored in a data object; the tainted value is leaked. Source and sink calls are distributed across the lifecycle.
* **FieldSensitivity4**: Field contents are sent before tainting the field.
* **InheritedObjects1**: Chooses an object’s actual type based on a conditional. Only one possible type leads to a leak.
* **ObjectSensitivity1**: Writes a tainted value into an object and an untainted one into another object of the same type. Leaks the untainted value.
* **ObjectSensitivity2**: Writes a tainted value into a field and then overwrites it with untainted data.

Inter-App Communication
-----------------------
* **IntentSink1**: A tainted value is leaked to another application using an intent.
* **IntentSink2**: Similar to IntentSink, but the value is sent out in a callback method defined in XML.
* **ActivityCommunication1**: Contains two activities that communicate using static fields.

Lifecycle
---------
* **BroadcastReceiverLifecycle1**: Calls to sources and sinks distributed across a broadcast receiver lifecycle.
* **ActivityLifecycle1**: Calls to sources and sinks distributed across an activity lifecycle.
* **ActivityLifecycle2**: Activity class inherited from a superclass containing the lifecycle method which leaks the tainted value.
* **ActivityLifecycle3**: Calls to sources and sinks distributed across instance state handling methods.
* **ActivityLifecycle4**: A tainted value is obtained on `onPause()` and leaked when the activity is restarted later.
* **ServiceLifecycle1**: Calls to sources and sinks distributed across a service lifecycle.

General Java
------------
* **Loop1**: Contains a simple loop and a data leak.
* **Loop2**: Retrieves location information through a callback and leaks it via nested loops.
* **SourceCodeSpecific1**: Uses unusual code construct `a = p ? b : c.`
* **StaticInitialization1**: Passes a tainted value into a static initialization method.
* **UnreachableCode**: Passes tainted data into a method that is never called.

Miscellaneous Android-Specific
------------------------------
* **PrivateDataLeak1**: Summary test case containing various challenges.
* **PrivateDataLeak2**: Leaks a value from a password field.
* **DirectLeak1**: The device id is read out and sent via SMS on the activity’s `onCreate()` event.
* **InactiveActivity**: Data leak in a disabled activity.
* **LogNoLeak**: Writes untainted data into a log file.

Implicit Flows
--------------
* **ImplicitFlow1-4**: Test cases for implicit flows.
