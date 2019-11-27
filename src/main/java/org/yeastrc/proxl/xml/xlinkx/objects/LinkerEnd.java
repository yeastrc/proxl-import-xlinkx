package org.yeastrc.proxl.xml.xlinkx.objects;

import java.util.Collection;

public class LinkerEnd {

    @Override
    public String toString() {
        return "LinkerEnd{" +
                "linkableResidues=" + linkableResidues +
                ", linksNTerminus=" + linksNTerminus +
                ", linksCTerminus=" + linksCTerminus +
                '}';
    }

    public LinkerEnd(Collection<String> linkableResidues, boolean linksNTerminus, boolean linksCTerminus) {
        this.linkableResidues = linkableResidues;
        this.linksNTerminus = linksNTerminus;
        this.linksCTerminus = linksCTerminus;
    }

    public Collection<String> getLinkableResidues() {
        return linkableResidues;
    }

    public boolean isLinksNTerminus() {
        return linksNTerminus;
    }

    public boolean isLinksCTerminus() {
        return linksCTerminus;
    }

    private Collection<String> linkableResidues;
    private boolean linksNTerminus;
    private boolean linksCTerminus;
}
