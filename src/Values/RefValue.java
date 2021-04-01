package Values;

import Types.InterfaceType;
import Types.RefType;

public class RefValue implements InterfaceValue {
    private InterfaceType refType;
    private int addr;

    public RefValue(InterfaceType refType, int addr) {
        this.refType = refType;
        this.addr = addr;
    }

    public InterfaceType getRefType() {
        return refType;
    }

    public int getAddr() {
        return addr;
    }

    @Override
    public InterfaceType getType() {
        return new RefType(refType);
    }

    @Override
    public String toString() {
        return "(" + addr + "; " + refType + ")";
    }

    @Override
    public InterfaceValue deepCopy() {
        return new RefValue(refType, addr);
    }
}
