-----
***Name: Adam Curley***<br />
***BUID: U63497650***

***Name: Lei Huang***<br />
***BUID: U67278956***

***Name: Sahana Subramanya Kowshik***<br />
***BUID: U43929102***

-----

***Classes***
1. Main: The main class that calls startGame of LegendsGame class.
2. RPGGame: An abstract class that stores common attributes to all role-playing games.
3. ValorGame: Implements main logic for the "Legends of Valor" game. Contains methods to start the game, create monsters, and fight.
4. Player: An abstract class stores attributes and functions common to all players.
5. ValorPlayer: Class to create players.
6. Heroes: An abstract class that stores common attributes and functions to all heroes.
7. Warrior: Class to create an instance of a warrior.
8. Sorcerer: Class to create an instance of a sorcerer.
9. Paladin: Class to create an instance of a paladin.
10. Monsters: An abstract class that stores common attributes and functions to all monsters.
11. Dragon: Class to create an instance of a dragon.
12. Exoskeleton: Class to create an instance of an exoskeleton.
13. Spirit: Class to create an instance of a spirit.
14. Cell: An abstract class that holds attributes to create a cell.
15. CellFactory: Used to implement the factory pattern to create a map using cells.
16. PlainCell: Class to create a plain cell.
17. NexusCell: Class to create a nexus cell.
18. InaccessibleCell: Class to create an inaccessible cell.
19. CaveCell: Class to create a cave cell.
20. BushCell: Class to create a bush cell.
21. KoulouCell: Class to create a koulou cell.
22. Board: An abstract class that holds attributes and functions common to all RPG games.
23. ValorBoard: Class to create a map/board. Contains methods to create boards, add players and move them.
24. Market: Class to create a market. Has methods to create and display all market items, and buy/sell items.
25. MarketItems: An abstract class that holds common attributes to all items.
26. Armory: Class to create armories.
27. Weaponry: Class to create weapons.
28. Potions: Class to create potions.
29. Spell: An abstract class that holds common attributes to all spells.
30. FireSpell: Class to create a fire spell.
31. IceSpell: Class to create an ice spell.
32. LightningSpell: Class to create a lightning spell.

***Interfaces***
1. Game: Stores common attributes to all games.
2. isAccessible: To check if a cell is accessible.
3. isBuyableSellable: To check if an item can be bought or sold.
4. isCastable: To check if an item is castable. Implements default method use to cast a spell.
5. isDrinkable: To check if an item is drinkable. Implements default method use to drink the potion.
6. isUsable: To check if an item can be used.
7. MarketInterface: Used with Market class to implement Facade Design Pattern to create a market.

***Helper classes***
1. GameFunctions: Contains the functions that are used to drive the game. This includes getting input and getting a boolean (of an event occurring) based on the given probability.
2. GameConstants: Contains the constants used in the card games. This includes a constant to set board size.
3. Music: Used to play music.
4. ASCIIArtGenerator: To create ASCII art. Taken reference from "https://www.quickprogrammingtips.com/java/ascii-art-generator-library-in-java.html".
5. Parser: Used to parse the input for config files and music files.
6. Display: Used to display map/board, heroes, monsters, legend, and list of armories, weapons, potions, and spells.

***Assumptions***
1. If a hero dies, he gets respawned at the nexus with half his health.
2. A hero is assumed to be able to hold one weapon and one armor at a time.
3. A hero can hold any number of potions or spells of the same level.
4. After the fight, the hero gets coins equal to monster's level * 100.

***Design Patterns***
1. Factory Pattern: Factory pattern is implemented in the ValorBoard class to create cells of type nexus, plain, cave, bush, koulou and inaccessible using CellFactory class.
2. Facade Pattern: Facade pattern is implemented to create the market. The main intention to use the facade pattern here is to allow the creation of market items only using the market object. This is achieved using MarketInterface.

***Bonus Features***
1. Implemented 2 design patterns
2. Added ASCII text art
3. Added color to the text
4. Added sound to the game
5. Implemented a parser class to parse the config files
6. Implemented level up

**Compile and run**<br />
*Extract the zip file into a folder.
All the below commands are run in the extracted directory*
1. cd src
2. javac Main.java
3. java Main

***How to Play***
1. Compile and run using the instructions mentioned above.
2. Enter player name.
3. Build the team by selecting the hero classes and the Id of the heroes.
4. Use the map and the location to move around. 
   1. Move (W/A/S/D)
   2. BackToNexus (B)
   3. Teleport (T)
   4. Check player Info (I)
   5. Check weapons Inventory (E)
   6. Show map (M)
   7. Quit (Q)
6. Items can be bought and sold in a Nexus cell.
7. Monsters can be fought using a weapon or casting a spell.


***Note:***
1. The code only works with compile and run steps specified due to the path given to parse the config and music files. Does not run with Intellij or Eclipse.
2. Config files are present in the Legends_Monsters_and_Heroes folder. 
4. There are 13 music files for sound at different stages.
