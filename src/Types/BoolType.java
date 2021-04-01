package Types;

public class BoolType implements InterfaceType {
    @Override
    public boolean equals(InterfaceType param) {
        return param instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }
}
