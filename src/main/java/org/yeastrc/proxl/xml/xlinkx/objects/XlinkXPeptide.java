package org.yeastrc.proxl.xml.xlinkx.objects;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class XlinkXPeptide {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XlinkXPeptide that = (XlinkXPeptide) o;
        return sequence.equals(that.sequence) &&
                Objects.equals(dynamicModifications, that.dynamicModifications) &&
                Objects.equals(nTerminalMod, that.nTerminalMod) &&
                Objects.equals(cTerminalMod, that.cTerminalMod) &&
                linkedPosition.equals(that.linkedPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence, dynamicModifications, nTerminalMod, cTerminalMod, linkedPosition);
    }

    /**
     * Get the string representation of this peptide that includes mods, in the form of:
     * n[16.04]PEP[12.29,15.99]TI[12.2932]DEc[111.22](22)
     */
    public String toString() {

        String str = "";

        for( int i = 1; i <= this.getSequence().length(); i++ ) {
            String r = String.valueOf( this.getSequence().charAt( i - 1 ) );
            str += r;

            if( this.getDynamicModifications() != null ) {
                List<String> modsAtPosition = new ArrayList<String>();

                if( this.getDynamicModifications().get( i ) != null ) {
                    for( XlinkXDynamicMod mod : this.getDynamicModifications().get( i ) ) {
                        modsAtPosition.add( mod.getMassDiff().setScale( 2, BigDecimal.ROUND_HALF_UP ).toString() );
                    }

                    if( modsAtPosition.size() > 0 ) {

                        // sort these strings on double values
                        Collections.sort( modsAtPosition, new Comparator<String>() {
                            public int compare(String s1, String s2) {
                                return Double.valueOf( s1 ).compareTo( Double.valueOf( s2 ) );
                            }
                        });

                        String modsString = StringUtils.join( modsAtPosition, "," );
                        str += "[" + modsString + "]";
                    }
                }
            }
        }

        if( this.getnTerminalMod() != null ) {
            str = "n[" + this.getnTerminalMod().getMassDiff().setScale( 2, BigDecimal.ROUND_HALF_UP ).toString() +"]" + str;
        }

        if( this.getcTerminalMod() != null ) {
            str = str + "c[" + this.getcTerminalMod().getMassDiff().setScale( 2, BigDecimal.ROUND_HALF_UP ).toString() +"]";
        }

        str += "(" + this.getLinkedPosition() + ")";

        return str;
    }

    public XlinkXPeptide(String sequence, Map<Integer, Collection<XlinkXDynamicMod>> dynamicModifications, XlinkXDynamicMod nTerminalMod, XlinkXDynamicMod cTerminalMod, Integer linkedPositions) {
        this.sequence = sequence;
        this.dynamicModifications = dynamicModifications;
        this.nTerminalMod = nTerminalMod;
        this.cTerminalMod = cTerminalMod;
        this.linkedPosition = linkedPositions;
    }

    public String getSequence() {
        return sequence;
    }

    public Map<Integer, Collection<XlinkXDynamicMod>> getDynamicModifications() {
        return dynamicModifications;
    }

    public XlinkXDynamicMod getnTerminalMod() {
        return nTerminalMod;
    }

    public XlinkXDynamicMod getcTerminalMod() {
        return cTerminalMod;
    }

    public Integer getLinkedPosition() {
        return linkedPosition;
    }

    private String sequence;
    private Map<Integer, Collection<XlinkXDynamicMod>> dynamicModifications;
    private XlinkXDynamicMod nTerminalMod;
    private XlinkXDynamicMod cTerminalMod;
    private Integer linkedPosition;

}
