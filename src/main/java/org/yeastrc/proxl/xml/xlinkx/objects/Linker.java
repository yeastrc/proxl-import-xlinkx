package org.yeastrc.proxl.xml.xlinkx.objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Linker {
    @Override
    public String toString() {
        return "Linker{" +
                "crosslinkMasses=" + crosslinkMasses +
                ", cleavedCrosslinkMasses=" + cleavedCrosslinkMasses +
                ", monolinkMasses=" + monolinkMasses +
                ", name='" + name + '\'' +
                ", isCleavable=" + isCleavable +
                ", linkerEnds=" + linkerEnds +
                ", formula='" + formula + '\'' +
                '}';
    }

    public Linker(Collection<Double> crosslinkMasses, Collection<Double> cleavedCrosslinkMasses, Collection<Double> monolinkMasses, String name, boolean isCleavable, List<LinkerEnd> linkerEnds, String formula) {
        this.crosslinkMasses = crosslinkMasses;
        this.cleavedCrosslinkMasses = cleavedCrosslinkMasses;
        this.monolinkMasses = monolinkMasses;
        this.name = name;
        this.isCleavable = isCleavable;
        this.linkerEnds = linkerEnds;
        this.formula = formula;
    }

    public Collection<Double> getCrosslinkMasses() {
        return crosslinkMasses;
    }

    public Collection<Double> getCleavedCrosslinkMasses() {
        return cleavedCrosslinkMasses;
    }

    public Collection<Double> getMonolinkMasses() {
        return monolinkMasses;
    }

    public String getName() {
        return name;
    }

    public boolean isCleavable() {
        return isCleavable;
    }

    public List<LinkerEnd> getLinkerEnds() {
        return linkerEnds;
    }

    public String getFormula() {
        return formula;
    }

    private Collection<Double> crosslinkMasses = new HashSet<>();
    private Collection<Double> cleavedCrosslinkMasses = new HashSet<>();
    private Collection<Double> monolinkMasses = new HashSet<>();
    private String name;
    private boolean isCleavable;
    private List<LinkerEnd> linkerEnds;
    private String formula;
}
