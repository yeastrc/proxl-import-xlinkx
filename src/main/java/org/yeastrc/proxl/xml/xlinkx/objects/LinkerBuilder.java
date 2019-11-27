package org.yeastrc.proxl.xml.xlinkx.objects;

import java.util.Collection;
import java.util.List;

public class LinkerBuilder {
    private Collection<Double> crosslinkMasses;
    private Collection<Double> cleavedCrosslinkMasses;
    private Collection<Double> monolinkMasses;
    private String name;
    private boolean isCleavable = false;
    private List<LinkerEnd> linkerEnds;
    private String formula;

    public LinkerBuilder setCrosslinkMasses(Collection<Double> crosslinkMasses) {
        this.crosslinkMasses = crosslinkMasses;
        return this;
    }

    public LinkerBuilder setCleavedCrosslinkMasses(Collection<Double> cleavedCrosslinkMasses) {
        this.cleavedCrosslinkMasses = cleavedCrosslinkMasses;
        return this;
    }

    public LinkerBuilder setMonolinkMasses(Collection<Double> monolinkMasses) {
        this.monolinkMasses = monolinkMasses;
        return this;
    }

    public LinkerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LinkerBuilder setIsCleavable(boolean isCleavable) {
        this.isCleavable = isCleavable;
        return this;
    }

    public LinkerBuilder setLinkerEnds(List<LinkerEnd> linkerEnds) {
        this.linkerEnds = linkerEnds;
        return this;
    }

    public LinkerBuilder setFormula(String formula) {
        this.formula = formula;
        return this;
    }

    public Linker createLinker() {
        return new Linker(crosslinkMasses, cleavedCrosslinkMasses, monolinkMasses, name, isCleavable, linkerEnds, formula);
    }
}