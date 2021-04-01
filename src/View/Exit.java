package View;

public class Exit extends Command {

    public Exit(String command, String details) {
        super(command, details);
    }

    @Override
    public void run() {
        System.exit(0);
    }
}
