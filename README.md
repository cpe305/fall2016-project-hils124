# Abandoned
#### A text-based mystery game
Players find themselves trapped in a house and have to collect and use items in order to find a way out.

*Hilary Schulz*
<hr>

### Setup

Clone source code to your machine on your terminal. <br>
&nbsp;&nbsp;Run: <br>
&nbsp;&nbsp;&nbsp;&nbsp;```mvn clean install```<br>
&nbsp;&nbsp;To Play Game: <br>
&nbsp;&nbsp;&nbsp;&nbsp;```mvn exec:java```
<hr>
### How to Play
This game is a terminal application and is completely text-based. Players move around, inspect objects and collect items by typing commands into the terminal.

###### Player Commands
<ul>
  <li>enter [PORTAL]</li>
  <li>inspect [ELEMENT]</li>
  <li>take [ITEM]</li>
  <li>turn [LEFT, RIGHT, AROUND]</li>
  <li>use [ITEM]</li>
  <li>view inventory</li>
  <li>quit</li>
</ul>

<hr>

### Source Code Documentation
#### abandoned.house
Package that stores all the classes that form the house:
<ul>
  <li>Rooms</li>
  <li>Walls</li>
  <li>Portals</li>
  <li>Containers</li>
</ul>

#### abandoned.game
Package that stores all the game logic and the initial creation of the house itself.

###### JSON Serialization
The house is built from a JSON file called *HouseData.json* which holds all the data for each room in the house. *HouseBuilder.java* reads this file in and serializes the information into a java object which it then returns.

```java
House house = mapper.readValue(new File("resources/HouseData.json"), House.class);
```

This house contains rooms, walls, portals, and containers that the player can now interact with.

#### abandoned.entities
Package that stores items and specific actions for each one.
###### Item Actions
Item actions are handled with Java enums found in *EntityUseType.java*. Each item has a parameter *useType* which holds an enum to indicate the item's action.
<ul>
  <li> keys -> <em>new KeyAction()</em></li>
  <li> scissors -> <em>new ScissorsAction()</em></li>
</ul>

Each action class has a *use()* method that is specific to that item. This method is called by item's *use()* method.

<hr>
