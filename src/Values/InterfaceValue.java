package Values;

import Types.InterfaceType;

public interface InterfaceValue {
    InterfaceType getType();
    InterfaceValue deepCopy();
}
