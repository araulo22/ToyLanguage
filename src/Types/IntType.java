package Types;

public class IntType implements InterfaceType {
    @Override
    public boolean equals(InterfaceType param) {
        return param instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
