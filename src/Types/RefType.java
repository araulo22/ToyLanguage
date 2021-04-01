package Types;

import Values.RefValue;

public class RefType implements InterfaceType {
    private InterfaceType inner;

    public RefType(InterfaceType inner) {
        this.inner = inner;
    }

    public InterfaceType getInner() {
        return inner;
    }

    public RefValue defaultValue() {
        return new RefValue(inner, 0);
    }

    @Override
    public boolean equals(InterfaceType param) {
        return param instanceof RefType;
    }

    @Override
    public String toString() {
        return "Ref(" + inner + ")";
    }
}
