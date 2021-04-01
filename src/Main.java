import View.Exit;
import View.RunExample;
import View.TextMenu;

public class Main {

    public static void main(String[] args) {
        TextMenu textMenu = new TextMenu();
        textMenu.addCommand(new Exit("exit", "Terminates the program"));
        textMenu.addCommand(new RunExample("1", "", 1));
        textMenu.addCommand(new RunExample("2", "", 2));
        textMenu.addCommand(new RunExample("3", "", 3));
        textMenu.addCommand(new RunExample("4", "", 4));
        textMenu.addCommand(new RunExample("5", "", 5));
        textMenu.addCommand(new RunExample("6", "", 6));
        textMenu.addCommand(new RunExample("7", "", 7));
        textMenu.addCommand(new RunExample("8", "", 8));
        textMenu.displayMenu();
    }
}
