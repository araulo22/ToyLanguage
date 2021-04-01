package Values;

import Types.InterfaceType;
import Types.StringType;

public class StringValue implements InterfaceValue {
    private String value;

    public StringValue(String str) {
        this.value = str;
    }

    public StringValue() {
        this.value = "";
    }

    public String getValue() {
        return value;
    }

    @Override
    public InterfaceType getType() {
        return new StringType();
    }

    public boolean equals(InterfaceValue param) {
        StringValue aux = (StringValue)param;
        return this.value.equals(aux.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public InterfaceValue deepCopy() {
        return new StringValue(value);
    }
}
