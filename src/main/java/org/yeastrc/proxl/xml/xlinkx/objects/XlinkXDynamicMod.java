package org.yeastrc.proxl.xml.xlinkx.objects;

import java.math.BigDecimal;

public class XlinkXDynamicMod {

    @Override
    public String toString() {
        return "XlinkXDynamicMod{" +
                "massDiff=" + massDiff +
                ", isMonolink=" + isMonolink +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XlinkXDynamicMod that = (XlinkXDynamicMod) o;

        if (isMonolink != that.isMonolink) return false;
        return massDiff.equals(that.massDiff);
    }

    @Override
    public int hashCode() {
        int result = massDiff.hashCode();
        result = 31 * result + (isMonolink ? 1 : 0);
        return result;
    }

    public XlinkXDynamicMod(BigDecimal massDiff, boolean isMonolink) {
        this.massDiff = massDiff;
        this.isMonolink = isMonolink;
    }

    public BigDecimal getMassDiff() {
        return massDiff;
    }

    public boolean isMonolink() {
        return isMonolink;
    }

    private BigDecimal massDiff;
    private boolean isMonolink;
}
