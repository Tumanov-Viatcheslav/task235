import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RobotProblem {
    public static void main(String[] args) {
        String commands = inputData();

        Robot robot = new Robot();

        System.out.println(robot.inputProgram(commands));
    }

    private static String inputData() {
        String commands = "";
        try(BufferedReader input = new BufferedReader(new FileReader("input.txt"))) {
            commands = input.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return commands;
    }
}