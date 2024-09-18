import java.util.ArrayDeque;
import java.util.TreeSet;

public class Robot {
    private final int MAX_STEPS = 50;
    private final Coordinates position = new Coordinates(MAX_STEPS, MAX_STEPS);
    private Direction direction = Direction.UP;
    private final RobotMap map = new RobotMap(MAX_STEPS);
    private final ArrayDeque<Action> commandsToDo = new ArrayDeque<>();
    private final TreeSet<Coordinates> path = new TreeSet<>(new CoordinatesComparator());

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

    private void savePositionInMemory() {
        path.add(position.copy());
    }

    private void stepForward() {
        savePositionInMemory();
        switch (direction) {
            case UP:
                position.y++;
                return;
            case LEFT:
                position.x--;
                return;
            case DOWN:
                position.y--;
                return;
            case RIGHT:
                position.x++;
        }
    }

    private boolean leaveTrace() {
        if (!map.traceOnMAP[position.x][position.y]) {
            map.traceOnMAP[position.x][position.y] = true;
            return true;
        }
        return false;
    }

    private int doActionTrace(Action action) {
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

    private int doAction(Action action) {
        return switch (action) {
            case FORWARD -> {
                stepForward();
                leaveTrace();
                yield path.contains(position) ? 1 : 0;
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
