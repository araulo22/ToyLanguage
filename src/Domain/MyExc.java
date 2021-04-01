package Domain;

public class MyExc extends Exception {
    String message;

    public MyExc(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ToyException{" + message + '\'' + '}';
    }
}
