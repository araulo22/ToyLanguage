package View;

import Domain.MyExc;

import java.io.IOException;

public abstract class Command {
    private String command;
    private String details;

    public Command(String command, String details) {
        this.command = command;
        this.details = details;
    }

    public abstract void run();

    public String getCommand() {
        return command;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
