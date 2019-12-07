package org.yeastrc.proxl.xml.xlinkx.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XlinkXReportedPeptide {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XlinkXReportedPeptide that = (XlinkXReportedPeptide) o;

        return peptides.equals(that.peptides);
    }

    @Override
    public int hashCode() {
        return peptides.hashCode();
    }

    /**
     * Create a new XlinkXReportedPeptide. The order of peptide1 and peptide2 doesn't matter, hashCode
     * evaluation will correctly match identical reported peptides regardless of order.
     *
     * @param peptide1
     * @param peptide2
     */
    public XlinkXReportedPeptide(XlinkXPeptide peptide1, XlinkXPeptide peptide2) {

        final int i = peptide1.toString().compareTo(peptide2.toString());
        this.peptides = new ArrayList<>(2);

        if( i <= 0 ) {
            this.peptides.add( peptide1 );
            this.peptides.add( peptide2 );

        } else {

            this.peptides.add(peptide2);
            this.peptides.add(peptide1);
        }


        this.peptides = Collections.unmodifiableList( this.peptides );
    }

    public String toString() {
            return this.getPeptides().get(0).toString() + "-" + this.getPeptides().get(1).toString();
    }

    public List<XlinkXPeptide> getPeptides() {
        return peptides;
    }

    private List<XlinkXPeptide> peptides;

}
