package Types;

public class StringType implements InterfaceType {

    @Override
    public boolean equals(InterfaceType param) {
        return param instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }
}
