package org.robot.mower.validator;

public class InputValidator {
    
    private static final String ROBOT_INPUT_PATTERN = "\\d+ \\d+ \\w+";
    private static final String ROOM_INPUT_PATTERN = "\\d+ \\d+";

    public static void validateRobotInput(String input) {
        if(!input.matches(ROBOT_INPUT_PATTERN)) {
            throw new IllegalArgumentException("Invalid input. Please enter (x,y, orientation) separated by a space. Got: " + input);
        }
    }

    public static void validateRoomInput(String input) {
        if(!input.matches(ROOM_INPUT_PATTERN)){
            throw new IllegalArgumentException("Invalid input. Please enter two integers (height, width) separated by a space. Got: " + input);
        }
    }
}