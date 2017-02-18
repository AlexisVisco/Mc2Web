# Getting started

## How to Build

The generated code has dependencies over external libraries like UniRest. These dependencies are defined in the ```composer.json``` file that comes with the SDK. 
To resolve these dependencies, we use the Composer package manager which requires PHP greater than 5.3.2 installed in your system. 
Visit [https://getcomposer.org/download/](https://getcomposer.org/download/) to download the installer file for Composer and run it in your system. 
Open command prompt and type ```composer --version```. This should display the current version of the Composer installed if the installation was successful.

* Using command line, navigate to the directory containing the generated files (including ```composer.json```) for the SDK. 
* Run the command ```composer install```. This should install all the required dependencies and create the ```vendor``` directory in your project directory.

![Building SDK - Step 1](https://apidocs.io/illustration/php?step=installDependencies&workspaceFolder=Mc2Web-PHP)

### [For Windows Users Only] Configuring CURL Certificate Path in php.ini

CURL used to include a list of accepted CAs, but no longer bundles ANY CA certs. So by default it will reject all SSL certificates as unverifiable. You will have to get your CA's cert and point curl at it. The steps are as follows:

1. Download the certificate bundle (.pem file) from [https://curl.haxx.se/docs/caextract.html](https://curl.haxx.se/docs/caextract.html) on to your system.
2. Add curl.cainfo = "PATH_TO/cacert.pem" to your php.ini file located in your php installation. “PATH_TO” must be an absolute path containing the .pem file.

```ini
[curl]
; A default value for the CURLOPT_CAINFO option. This is required to be an
; absolute path.
;curl.cainfo =
```

## How to Use

The following section explains how to use the Mc2Web library in a new project.

### 1. Open Project in an IDE

Open an IDE for PHP like PhpStorm. The basic workflow presented here is also applicable if you prefer using a different editor or IDE.

![Open project in PHPStorm - Step 1](https://apidocs.io/illustration/php?step=openIDE&workspaceFolder=Mc2Web-PHP)

Click on ```Open``` in PhpStorm to browse to your generated SDK directory and then click ```OK```.

![Open project in PHPStorm - Step 2](https://apidocs.io/illustration/php?step=openProject0&workspaceFolder=Mc2Web-PHP)     

### 2. Add a new Test Project

Create a new directory by right clicking on the solution name as shown below:

![Add a new project in PHPStorm - Step 1](https://apidocs.io/illustration/php?step=createDirectory&workspaceFolder=Mc2Web-PHP)

Name the directory as "test"

![Add a new project in PHPStorm - Step 2](https://apidocs.io/illustration/php?step=nameDirectory&workspaceFolder=Mc2Web-PHP)
   
Add a PHP file to this project

![Add a new project in PHPStorm - Step 3](https://apidocs.io/illustration/php?step=createFile&workspaceFolder=Mc2Web-PHP)

Name it "testSDK"

![Add a new project in PHPStorm - Step 4](https://apidocs.io/illustration/php?step=nameFile&workspaceFolder=Mc2Web-PHP)

Depending on your project setup, you might need to include composer's autoloader in your PHP code to enable auto loading of classes.

```PHP
require_once "../vendor/autoload.php";
```

It is important that the path inside require_once correctly points to the file ```autoload.php``` inside the vendor directory created during dependency installations.

![Add a new project in PHPStorm - Step 4](https://apidocs.io/illustration/php?step=projectFiles&workspaceFolder=Mc2Web-PHP)

After this you can add code to initialize the client library and acquire the instance of a Controller class. Sample code to initialize the client library and using controller methods is given in the subsequent sections.

### 3. Run the Test Project

To run your project you must set the Interpreter for your project. Interpreter is the PHP engine installed on your computer.

Open ```Settings``` from ```File``` menu.

![Run Test Project - Step 1](https://apidocs.io/illustration/php?step=openSettings&workspaceFolder=Mc2Web-PHP)

Select ```PHP``` from within ```Languages & Frameworks```

![Run Test Project - Step 2](https://apidocs.io/illustration/php?step=setInterpreter0&workspaceFolder=Mc2Web-PHP)

Browse for Interpreters near the ```Interpreter``` option and choose your interpreter.

![Run Test Project - Step 3](https://apidocs.io/illustration/php?step=setInterpreter1&workspaceFolder=Mc2Web-PHP)

Once the interpreter is selected, click ```OK```

![Run Test Project - Step 4](https://apidocs.io/illustration/php?step=setInterpreter2&workspaceFolder=Mc2Web-PHP)

To run your project, right click on your PHP file inside your Test project and click on ```Run```

![Run Test Project - Step 5](https://apidocs.io/illustration/php?step=runProject&workspaceFolder=Mc2Web-PHP)

## How to Test

Unit tests in this SDK can be run using PHPUnit. 

1. First install the dependencies using composer including the `require-dev` dependencies.
2. Run `vendor\bin\phpunit --verbose` from commandline to execute tests. If you have 
   installed PHPUnit globally, run tests using `phpunit --verbose` instead.

You can change the PHPUnit test configuration in the `phpunit.xml` file.

## Initialization

### Authentication
In order to setup authentication and initialization of the API client, you need the following information.

| Parameter | Description |
|-----------|-------------|
| basicAuthUserName | The username to use with basic authentication |
| basicAuthPassword | The password to use with basic authentication |



API client can be initialized as following.

```php
// Configuration parameters and credentials
$basicAuthUserName = "basicAuthUserName"; // The username to use with basic authentication
$basicAuthPassword = "basicAuthPassword"; // The password to use with basic authentication

$client = new Mc2WebLib\Mc2WebClient($basicAuthUserName, $basicAuthPassword);
```

## Class Reference

### <a name="list_of_controllers"></a>List of Controllers

* [APIController](#api_controller)

### <a name="api_controller"></a>![Class: ](https://apidocs.io/img/class.png ".APIController") APIController

#### Get singleton instance

The singleton instance of the ``` APIController ``` class can be accessed from the API Client.

```php
$client = $client->getClient();
```

#### <a name="get_player_banned"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerBanned") getPlayerBanned

> List of banned players.


```php
function getPlayerBanned()
```

#### Example Usage

```php

$result = $client->getPlayerBanned();

```


#### <a name="create_player_kick"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerKick") createPlayerKick

> Kick a player from server.
> 
> 


```php
function createPlayerKick(
        $reason,
        $uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| reason |  ``` Required ```  | Reason of the kick |
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$reason = 'Tu as été kick';
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerKick($reason, $uuid);

```


#### <a name="get_player_all"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerAll") getPlayerAll

> Get all detailled informations about each players.


```php
function getPlayerAll()
```

#### Example Usage

```php

$result = $client->getPlayerAll();

```


#### <a name="create_player_teleport"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerTeleport") createPlayerTeleport

> Teleport player at target location.


```php
function createPlayerTeleport(
        $uuid,
        $location)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |
| location |  ``` Required ```  | TODO: Add a parameter description |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';
$location = '{ "x":0, "y":0, "z":100, "world":"world" }';

$result = $client->createPlayerTeleport($uuid, $location);

```


#### <a name="get_faction_get_name_faction"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getFactionGetNameFaction") getFactionGetNameFaction

> Get a faction.
> 


```php
function getFactionGetNameFaction($faction)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| faction |  ``` Required ```  | Name of the faction |



#### Example Usage

```php
$faction = 'kwizzy';

$result = $client->getFactionGetNameFaction($faction);

```


#### <a name="get_player_all_lite"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerAllLite") getPlayerAllLite

> Get all crutial informations about each players.


```php
function getPlayerAllLite()
```

#### Example Usage

```php

$result = $client->getPlayerAllLite();

```


#### <a name="get_server"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getServer") getServer

> Get information about the server.


```php
function getServer()
```

#### Example Usage

```php

$result = $client->getServer();

```


#### <a name="get_player_whitelisted"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerWhitelisted") getPlayerWhitelisted

> List of whitelisted players.


```php
function getPlayerWhitelisted()
```

#### Example Usage

```php

$result = $client->getPlayerWhitelisted();

```


#### <a name="create_player_get_info_info"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerGetInfoInfo") createPlayerGetInfoInfo

> Get one information among all detailled informations.


```php
function createPlayerGetInfoInfo(
        $info,
        $uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| info |  ``` Required ```  | Information |
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$info = 'inventoy';
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerGetInfoInfo($info, $uuid);

```


#### <a name="get_faction_player_uuid"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getFactionPlayerUuid") getFactionPlayerUuid

> Get a player from faction.


```php
function getFactionPlayerUuid($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$client->getFactionPlayerUuid($uuid);

```


#### <a name="get_player_count"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerCount") getPlayerCount

> Get number of online players.


```php
function getPlayerCount()
```

#### Example Usage

```php

$result = $client->getPlayerCount();

```


#### <a name="get_player_opped"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getPlayerOpped") getPlayerOpped

> List of opped players.


```php
function getPlayerOpped()
```

#### Example Usage

```php

$result = $client->getPlayerOpped();

```


#### <a name="create_player_offline_name"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerOfflineName") createPlayerOfflineName

> Get information about an offline player.


```php
function createPlayerOfflineName($name)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| name |  ``` Required ```  | Name of the player |



#### Example Usage

```php
$name = '_Kwizzy';

$result = $client->createPlayerOfflineName($name);

```


#### <a name="create_player_clear"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerClear") createPlayerClear

> Clear a player from the server.
> 


```php
function createPlayerClear($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerClear($uuid);

```


#### <a name="get_server_plugins"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getServerPlugins") getServerPlugins

> Get complex schema of plugin descriptions


```php
function getServerPlugins()
```

#### Example Usage

```php

$result = $client->getServerPlugins();

```


#### <a name="create_player_deop"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerDeop") createPlayerDeop

> Deop a player.


```php
function createPlayerDeop($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerDeop($uuid);

```


#### <a name="create_player_info"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerInfo") createPlayerInfo

> Get all detailled informations about the player.


```php
function createPlayerInfo($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerInfo($uuid);

```


#### <a name="create_player_offline_uuid"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerOfflineUuid") createPlayerOfflineUuid

> Get information about an offline player.


```php
function createPlayerOfflineUuid($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerOfflineUuid($uuid);

```


#### <a name="create_server_broadcast"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createServerBroadcast") createServerBroadcast

> Broadcast message to the server.


```php
function createServerBroadcast($message)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| message |  ``` Required ```  | Message to broadcast |



#### Example Usage

```php
$message = 'C\'est un message';

$result = $client->createServerBroadcast($message);

```


#### <a name="create_player_whitelist"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerWhitelist") createPlayerWhitelist

> Whitelist a player.


```php
function createPlayerWhitelist($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerWhitelist($uuid);

```


#### <a name="create_player_unban"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerUnban") createPlayerUnban

> Unban a player from the server.


```php
function createPlayerUnban($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerUnban($uuid);

```


#### <a name="get_world_all"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getWorldAll") getWorldAll

> Get all informations about the world


```php
function getWorldAll()
```

#### Example Usage

```php

$result = $client->getWorldAll();

```


#### <a name="create_player_feed"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerFeed") createPlayerFeed

> Feed a player.


```php
function createPlayerFeed($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerFeed($uuid);

```


#### <a name="create_player_ban"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerBan") createPlayerBan

> Ban a player from the server.


```php
function createPlayerBan($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerBan($uuid);

```


#### <a name="get_server_plugins_lite"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getServerPluginsLite") getServerPluginsLite

> Get name of all plugins.


```php
function getServerPluginsLite()
```

#### Example Usage

```php

$result = $client->getServerPluginsLite();

```


#### <a name="create_player_unwhitelist"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerUnwhitelist") createPlayerUnwhitelist

> Remove from whitelist a player.


```php
function createPlayerUnwhitelist($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerUnwhitelist($uuid);

```


#### <a name="create_player_heal"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerHeal") createPlayerHeal

> Heal a player.


```php
function createPlayerHeal($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerHeal($uuid);

```


#### <a name="create_world_get_block_at"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createWorldGetBlockAt") createWorldGetBlockAt

> Get block at location.


```php
function createWorldGetBlockAt($location)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| location |  ``` Required ```  | Sample: { "x":0, "y":0, "z":100, "world":"world" } |



#### Example Usage

```php
$location = '{ "x":0, "y":0, "z":100, "world":"world" }';

$result = $client->createWorldGetBlockAt($location);

```


#### <a name="get_world_name"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.getWorldName") getWorldName

> Get information about a world.


```php
function getWorldName($name)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| name |  ``` Required ```  | Name of the world |



#### Example Usage

```php
$name = 'world';

$result = $client->getWorldName($name);

```


#### <a name="create_player_op"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerOp") createPlayerOp

> Op a player.


```php
function createPlayerOp($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerOp($uuid);

```


#### <a name="create_player_info_lite"></a>![Method: ](https://apidocs.io/img/method.png ".APIController.createPlayerInfoLite") createPlayerInfoLite

> Get some crutial informations about the player.
> 


```php
function createPlayerInfoLite($uuid)
```

#### Parameters

| Parameter | Tags | Description |
|-----------|------|-------------|
| uuid |  ``` Required ```  | Uuid of the player |



#### Example Usage

```php
$uuid = 'ba804244-3406-4d0e-9e80-628513ac930b';

$result = $client->createPlayerInfoLite($uuid);

```


[Back to List of Controllers](#list_of_controllers)



