package View;

import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    private HashMap<String, Command> commands;

    public TextMenu() {
        this.commands = new HashMap<String, Command>();
    }

    public void addCommand(Command command) {
        commands.put(command.getCommand(), command);
    }

    private void printMenu() {
        for(Command command: commands.values()) {
            String cmd = command.getCommand() + " - " + command.getDetails();
            System.out.println(cmd);
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Input > ");
            String cmdtxt = scanner.nextLine();
            Command cmd = commands.get(cmdtxt);
            if(cmd == null) {
                System.out.println("Command not found!");
            } else {
                cmd.run();
            }
        }
    }
}
