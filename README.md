DroidBench
==========
DroidBench is an open test suite for evaluating the
effectiveness of taint-analysis tools specifically for Android apps. Version 1.0 comprises the following test cases:

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
