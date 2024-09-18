import java.util.ArrayDeque;

public class Robot {
    private final int MAX_STEPS = 50;
    private int x = 0, y = 0;
    private Direction direction = Direction.UP;
    private final RobotMap map = new RobotMap(MAX_STEPS);
    private ArrayDeque<Action> commandsToDo = new ArrayDeque<>();

    private void turnLeft() {
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                return;
            case LEFT:
                direction = Direction.DOWN;
                return;
            case DOWN:
                direction = Direction.RIGHT;
                return;
            case RIGHT:
                direction = Direction.UP;
        }
    }

    private void turnRight() {
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                return;
            case RIGHT:
                direction = Direction.DOWN;
                return;
            case DOWN:
                direction = Direction.LEFT;
                return;
            case LEFT:
                direction = Direction.UP;
        }
    }

    private void stepForward() {
        switch (direction) {
            case UP:
                y++;
                return;
            case LEFT:
                x--;
                return;
            case DOWN:
                y--;
                return;
            case RIGHT:
                x++;
        }
    }

    private boolean leaveTrace() {
        if (!map.traceOnMAP[x + MAX_STEPS][y + MAX_STEPS]) {
            map.traceOnMAP[x + MAX_STEPS][y + MAX_STEPS] = true;
            return true;
        }
        return false;
    }

    private int doAction(Action action) {
        return switch (action) {
            case FORWARD -> {
                stepForward();
                yield leaveTrace() ? 0 : 1;
            }
            case LEFT -> {
                turnLeft();
                yield -1;
            }
            case RIGHT -> {
                turnRight();
                yield -1;
            }
        };
    }

    private void parseCommands(String commands) {
        for (char c : commands.toCharArray()) {
            switch (c) {
                case 'S':
                    commandsToDo.add(Action.FORWARD);
                    break;
                case 'L':
                    commandsToDo.add(Action.LEFT);
                    break;
                case 'R':
                    commandsToDo.add(Action.RIGHT);
                    break;
            }
        }
    }

    public int inputProgram(String commands) {
        int stepsCounter = 0;
        parseCommands(commands);
        while (!commandsToDo.isEmpty()) {
            switch (doAction(commandsToDo.pop())) {
                case 0:
                    stepsCounter++;
                    break;
                case 1:
                    return ++stepsCounter;
            }
        }
        return -1;
    }
}
