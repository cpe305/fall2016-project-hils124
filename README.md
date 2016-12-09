# Abandoned
#### A text-based mystery game
Players find themselves trapped in a house and have to collect and use items in order to find a way out.

*Hilary Schulz*
<hr>
![](https://github.com/cpe305/fall2016-project-hils124/blob/master/Docs/AbandonedGamePlay2.png?raw=true)
<hr>
## Setup

Clone source code to your machine on your terminal. <br>
&nbsp;&nbsp;Run: <br>
&nbsp;&nbsp;&nbsp;&nbsp;```mvn clean install```<br>
&nbsp;&nbsp;To Play Game: <br>
&nbsp;&nbsp;&nbsp;&nbsp;```mvn exec:java```
<hr>
## How to Play
This game is a terminal application and is completely text-based. Players move around, inspect objects and collect items by typing commands into the terminal.

### Player Commands
<ul>
  <li>describe wall</li>
  <li>enter [PORTAL]</li>
  <li>inspect [ELEMENT]</li>
  <li>take [ITEM]</li>
  <li>turn [LEFT, RIGHT, AROUND]</li>
  <li>use [ITEM]</li>
  <li>view inventory</li>
  <li>quit</li>
</ul>
![](https://raw.githubusercontent.com/cpe305/fall2016-project-hils124/master/Docs/AbandonedGamePlay1.png)

<hr>

## Design Layout

### Floor Plan
![](https://raw.githubusercontent.com/cpe305/fall2016-project-hils124/master/Docs/FloorPlanAbandoned.png)

### Software Architecture
![](https://raw.githubusercontent.com/cpe305/fall2016-project-hils124/master/Docs/architectdesign.png)

#### Presentation Layer
User interaction with the terminal.
Users type commands directly in the command prompt and visually see the narration of the game.

#### Business Logic Layer
Handles business logic of the application.
Includes item actions and player commands
<ul>
  <li><em>EntityUseType.java</em></li>
  <li><em>CommandProcessor.java</em></li>
</ul>

#### Data Layer
Handles saving and loading of the game into files.
Related Classes:
<ul>
  <li><em>GameSaver.java</em></li>
  <li><em>GameLoader.java</em></li>
</ul>

Files:
  <li><em>HouseData.json</em></li>
  <li><em>saveHouse.json</em></li>
  <li><em>savePlayer.json</em></li>
</ul>

## Source Code Documentation
### abandoned.house
Package that stores all the classes that form the house:
<ul>
  <li>Rooms</li>
  <li>Walls</li>
  <li>Portals</li>
  <li>Containers</li>
</ul>

#### Class Diagram
![](https://raw.githubusercontent.com/cpe305/fall2016-project-hils124/master/Docs/ClassDiagramAbandoned.png)

### abandoned.game
Package that stores all the game logic and the initial creation of the house itself.

#### JSON Serialization
The house is built from a JSON file called *HouseData.json* which holds all the data for each room in the house. *HouseBuilder.java* reads this file in and serializes the information into a java object which it then returns.

```java
House house = mapper.readValue(new File("resources/HouseData.json"), House.class);
```

This house contains rooms, walls, portals, and containers that the player can now interact with.

### abandoned.entities
Package that stores items and specific actions for each one.
#### Item Actions
Item actions are handled with Java enums found in *EntityUseType.java*. Each item has a parameter *useType* which holds an enum to indicate the item's action.
<ul>
  <li> keys -> <em>new KeyAction()</em></li>
  <li> scissors -> <em>new ScissorsAction()</em></li>
</ul>

Each action class has a *use()* method that is specific to that item. This method is called by item's *use()* method.

### abandoned.commands
Package that stores player commands and their specific actions
#### Command Pattern
Player actions are handled with the Command Pattern. There is a Command interface and multiple Command classes (such as SaveCommand, EnterCommand, etc...) that implement this interface.

<em>Invoker.java</em> sets the current Command action to be run. The function for each specific command is located in <em>CommandProcessor.java</em>

<hr>

## Design Patterns
### Singleton Pattern
Creates one instance of a house and a player.
  <li><em>GlobalHouse.java</em></li>
  <li><em>GlobalPlayer.java</em></li>

##### Benefits:
  <li>All files able to access the current house and player</li>
  <li>Avoids conflicting requests for the same resource</li>

### Command Pattern
Allows the requester of a particular command to be decoupled from the object that performs the command
##### Related Classes:
  <li><em>Invoker.java</em></li>
  <li><em>Command.java</em></li>
  <li><em>CommandProcessor.java</em></li>
```java
      Initialize.cmdProcess.setProcess(name);
      switch (command) {
        case "describe": {
          Initialize.invoker.executeCommand(Initialize.describeCmd);
          break;
        }
        case "enter": {
          Initialize.invoker.executeCommand(Initialize.enterCmd);
          break;
        }
        case "inspect": {
          Initialize.invoker.executeCommand(Initialize.inspectCmd);
          break;
        }
      }
```
##### Class Diagram
