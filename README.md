# RobotMower
Who does not love robot mowers
# Prerequisites
## Requirements
1. Three inputs from System.in: 
   1. Room size (width, height), 
   2. Robot position and orientation (x,y, orientation) where orientation is N,S,W,E and x,y is natural numbers
   3. Define commands Left, Right, Forward denoted as L,R,F in sequence without space.

1. Output is expected to the following with examples within `()`
   1. Room size (5 5)
   2. Robot original position (0 0 E)
   3. Sequence of commands (RFLFFLRF)
   4. `Report:` with current position (Report: 3 1 E)

3. Clarifications:
   1. origo is defined as upper left corner
   2. Robot can start from (0,0) up to (room_width-1, room_height-1)


## Limitations
To simplify some limitations are set:
1. No support for lower case
2. If wrong input is provided the program will throw an error and stop.


# How to run
Project is made with Intellij using maven.

To run the test and compile the program run `mvn clean install`

To run the program run `mvn compile exec:java`

# Logging
Change loglevel within logback.xml.

Enable visualization.enabled in application.yml and get some fine visualization. The arrrow visualize the direction and position of the robot. 
```
    1   2   3   4   5  
  +---+---+---+---+---+ 
1 |   |   |   |   |   |
  +---+---+---+---+---+
2 |   |   |   |   |   |
  +---+---+---+---+---+
3 |   |   |   |   | v |
  +---+---+---+---+---+
4 |   |   |   |   |   |
  +---+---+---+---+---+
5 |   |   |   |   |   |
  +---+---+---+---+---+
```


# Future
* Since Simulation might take a lot of parameters for bigger projects a builder pattern could be better than taking everything as parameter
* Factory method is also a bit overkill but easier to read than a Ternary operator
