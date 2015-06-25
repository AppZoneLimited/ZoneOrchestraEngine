## Zone Orchestra Engine
This is the base Engine Library for the Zone framework for Android.

### Contents
This library contains three(3) major modules :

1. The Workflow Engine.

2. Script Interpreter Engine.

3. Script Interpreter (Predefined Functions).

This readme would be updated as more commits are made.

> Updates and commits would be referenced using a code number notation.

> Example : Update 3.0 means update 0 of Module 3 (Script Interpreter (Predefined Functions)).

> Code samples would be removed eventually and an example full android project would be built.

### Update 3.0 [master 75426dc](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/75426dceddab874645f6d0750f794905a0333bfb)

1. Added QR Code decoding with the ZXing library port.

- This update allows for scanning of QRCodes and handling results as deemed appropriate.

#### Sample Code

[QRCode Sample](https://github.com/AppZoneLimited/ZoneTestApplication/blob/master/src/com/example/testzone/QRScannerActivity.java)

### Update 3.1 [master 9bfa0f1e46](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/9bfa0f1e466b30c7cf3e424436c455c8c5a48f46)

1. Added PhoneOptions.java interface, PhoneOptionsEnum.java, PhoneOptionsFactory.java and PhoneContacts.java abstraction. Example usage added to the TestZone Android Project.

- This update allows pulling of contacts from user device and subsequent jsonfying it to allow easy sending over network.

#### Sample Code

[PhoneContacts Sample](https://github.com/AppZoneLimited/ZoneTestApplication/blob/master/src/com/example/testzone/MainActivity.java)

### Update 2.0 [master 2b9b6ce6](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/2b9b6ce6bbdc4061c038a6017e06fe90757c3ba6)

1. AlphaBuild for Script Interpreter Engine (Using JavaScript as Language of Choice)

- This update releases an alpha build version for the JS interpreter engine, can execute single line scripts, currently working on refining batched scripts in form of functions.

#### Sample Code

[Script Interpreter Sample](https://github.com/AppZoneLimited/ZoneTestApplication/blob/master/src/com/example/testzone/JSEngineActivity.java)

### Update 2.1 [master 0aa0f4f](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/0aa0f4f650ea1c1fb743a88100b393eecee7b955)

1. BetaBuild for Script Interpreter Engine (Using JavaScript as Language of Choice)

- This update releases a beta build version for the JS interpreter engine, can execute functions to enable batched scripting.

#### Sample Code

[Script Interpreter Sample](https://github.com/AppZoneLimited/ZoneTestApplication/blob/master/src/com/example/testzone/JSEngineActivity.java)


### Update 2.2 [master ea62463](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/ea624634461759af1113f6025c5039825adbfbd9)

1. BetaBuild of the Encryption/Hashing Module

- This update releases a beta build version for the Encryption/Hashing aspect of the Script Intepreter.

#### Sample Code

[Encryption/Hashing Sample](https://github.com/AppZoneLimited/ZoneTestApplication/blob/master/src/com/example/testzone/EncryptActivity.java)

### Update 1.0 [master 60dbac1](https://github.com/AppZoneLimited/ZoneOrchestraEngine/commit/60dbac1b30728cbe7bbf294dcec9533bf72b0386)

1. Flow Parsing Engine

- This update releases a engine for parsing flows.

#### Sample Code

> Not available yet