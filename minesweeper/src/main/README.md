# Minesweeper

Description
- 
Minesweeper is single-player logic-based computer game played on rectangular board whose object is to locate a predetermined number of randomly-placed "mines" in the shortest possible time by clicking on "safe" squares while avoiding the squares with mines. If the player clicks on a mine, the game ends. Otherwise, a number between 0 and 8 is displayed that identifies the total number of mines present in the eight neighboring squares. Therefore, finding a square containing "8" indicated that all eight adjacent squares contain mines, while if a zero (displayed as a blank) is uncovered, there are no mines in the surrounding squares. A square suspected of containing a mine may be marked with flag.

The game will store the scores of players and make you able to see highcores in the game.

# Tests for Minesweeper

test/java/minesweeper contains mvn tests for the minesweeper. will play out games in different ways to test if the functionality that we want is working. Meaning that it tests out different scenarios where the game can easily crash if somthing is wrong.