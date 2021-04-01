package Values;

import Types.IntType;
import Types.InterfaceType;

public class IntValue implements InterfaceValue {
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public InterfaceType getType() {
        return new IntType();
    }

    public boolean equals(InterfaceValue param) {
        IntValue aux = (IntValue)param;
        return this.value == aux.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public InterfaceValue deepCopy() {
        return new IntValue(value);
    }
}
