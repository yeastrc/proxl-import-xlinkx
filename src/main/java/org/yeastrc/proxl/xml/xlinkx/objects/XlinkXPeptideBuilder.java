package org.yeastrc.proxl.xml.xlinkx.objects;

import java.util.Collection;
import java.util.Map;

public class XlinkXPeptideBuilder {
    private String sequence;
    private Map<Integer, Collection<XlinkXDynamicMod>> dynamicModifications;
    private XlinkXDynamicMod nTerminalMod;
    private XlinkXDynamicMod cTerminalMod;
    private Integer linkedPositions;

    public XlinkXPeptideBuilder setSequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public XlinkXPeptideBuilder setDynamicModifications(Map<Integer, Collection<XlinkXDynamicMod>> dynamicModifications) {
        this.dynamicModifications = dynamicModifications;
        return this;
    }

    public XlinkXPeptideBuilder setnTerminalMod(XlinkXDynamicMod nTerminalMod) {
        this.nTerminalMod = nTerminalMod;
        return this;
    }

    public XlinkXPeptideBuilder setcTerminalMod(XlinkXDynamicMod cTerminalMod) {
        this.cTerminalMod = cTerminalMod;
        return this;
    }

    public XlinkXPeptideBuilder setLinkedPositions(Integer linkedPositions) {
        this.linkedPositions = linkedPositions;
        return this;
    }

    public XlinkXPeptide createXlinkXPeptide() {
        return new XlinkXPeptide(sequence, dynamicModifications, nTerminalMod, cTerminalMod, linkedPositions);
    }
}