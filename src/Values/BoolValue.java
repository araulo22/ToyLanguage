package Values;

import Types.BoolType;
import Types.InterfaceType;

public class BoolValue implements InterfaceValue {
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public InterfaceType getType() {
        return new BoolType();
    }

    public boolean equals(InterfaceValue param) {
        BoolValue aux = (BoolValue)param;
        return this.value == aux.value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public InterfaceValue deepCopy() {
        return new BoolValue(value);
    }
}
