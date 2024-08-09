package org.robot.mower.validator;

import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

public class RobotValidator {

    public static void validateRobotMovement(Robot robot, Room room) {
        int dx = robot.getX();
        int dy= robot.getY();


        switch(robot.getOrientation()){
            case N: //Towards min size of room
                dy+= Orientation.N.getAction();
                break;
            case E: //Towards max size of room
                dx+= Orientation.E.getAction();
                break;
            case S: //Towards max size of room
                dy+= Orientation.S.getAction();
                break;
            case W: //Towards min size of room
                dx+= Orientation.W.getAction();
                break;
        }

        if (isOutsideBoundaries(dx, dy, room)) {
            throw new IllegalArgumentException("Attempt to move the robot %s - Outside the room boundaries".formatted(robot.getOrientation().getDirection()));
        }
    }

    public static boolean isOutsideBoundaries(int x, int y, Room room) {
        return isOutsideHeightLimits(y, room.getHeight()) || isOutsideWidthLimits(x, room.getWidth());
    }

    private static boolean isOutsideHeightLimits(int y, int roomHeight) {
        return y < 0 || y >= roomHeight;
    }

    private static boolean isOutsideWidthLimits(int x, int roomWidth) {
        return x < 0 || x >= roomWidth;
    }

    public static void withinRoomBoundaries(Robot robot, Room room) {
        if (isOutsideBoundaries(robot.getX(), robot.getY(), room)) {
            throw new IllegalStateException("Robot is not allowed to exist outside room boundaries");
        }
    }
}