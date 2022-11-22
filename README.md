

# Tema POO  - GwentStone

### Arghir Monica-Andreea 322CA

For implementing the game I created several packages and classes to be able to have a well organized code.

Starting from the commands package, there are two classes: the one that checks every command and executes the code for it and the class that verifies and treats the errors.
At the beginning of the commands class I initialize the game and get all the data, so I can work around it.

Moving to the game package, this contains the statistics for the last commands and a class which holds the data for a single game such as the players, the game table, the turn of the player.

The gameDetails package is used for copying the data given at the input, so I can work and not ruin it. Every class is used for deep copying the data of each given input class.

Finally, the output package holds all the json formats for each command and error and puts it in the output given.