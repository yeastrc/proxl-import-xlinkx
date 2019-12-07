package org.yeastrc.proxl.xml.xlinkx.database;

import java.math.BigDecimal;

public class PsmDTOBuilder {
    private int id;
    private String sequenceA;
    private int peptidePositionA;
    private int numberMatchesA;
    private BigDecimal ptmScoreA;
    private BigDecimal sequenceCoverageA;
    private String modificationConnectionsA;
    private String sequenceB;
    private int peptidePositionB;
    private int numberMatchesB;
    private BigDecimal ptmScoreB;
    private BigDecimal sequenceCoverageB;
    private String modificationConnectionsB;
    private String spectrumFilePath;
    private String crosslinkReagent;
    private int scanNumber;
    private BigDecimal retentionTime;
    private BigDecimal precursorMz;
    private int precursorChargeState;
    private BigDecimal reporterScore;
    private BigDecimal score;
    private BigDecimal scoreDifference;
    private BigDecimal deltaMass;
    private BigDecimal fractionIonsMatchedBSeries;
    private BigDecimal fractionIonsMatchedYSeries;
    private BigDecimal fragmentationEfficiency;
    private BigDecimal percolatorSVMScore;
    private BigDecimal percolatorPValue;
    private BigDecimal percolatorQValue;
    private BigDecimal percolatorPEP;
    private BigDecimal validatorQValue;

    public PsmDTOBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PsmDTOBuilder setSequenceA(String sequenceA) {
        this.sequenceA = sequenceA;
        return this;
    }

    public PsmDTOBuilder setPeptidePositionA(int peptidePositionA) {
        this.peptidePositionA = peptidePositionA;
        return this;
    }

    public PsmDTOBuilder setNumberMatchesA(int numberMatchesA) {
        this.numberMatchesA = numberMatchesA;
        return this;
    }

    public PsmDTOBuilder setPtmScoreA(BigDecimal ptmScoreA) {
        this.ptmScoreA = ptmScoreA;
        return this;
    }

    public PsmDTOBuilder setSequenceCoverageA(BigDecimal sequenceCoverageA) {
        this.sequenceCoverageA = sequenceCoverageA;
        return this;
    }

    public PsmDTOBuilder setModificationConnectionsA(String modificationConnectionsA) {
        this.modificationConnectionsA = modificationConnectionsA;
        return this;
    }

    public PsmDTOBuilder setSequenceB(String sequenceB) {
        this.sequenceB = sequenceB;
        return this;
    }

    public PsmDTOBuilder setPeptidePositionB(int peptidePositionB) {
        this.peptidePositionB = peptidePositionB;
        return this;
    }

    public PsmDTOBuilder setNumberMatchesB(int numberMatchesB) {
        this.numberMatchesB = numberMatchesB;
        return this;
    }

    public PsmDTOBuilder setPtmScoreB(BigDecimal ptmScoreB) {
        this.ptmScoreB = ptmScoreB;
        return this;
    }

    public PsmDTOBuilder setSequenceCoverageB(BigDecimal sequenceCoverageB) {
        this.sequenceCoverageB = sequenceCoverageB;
        return this;
    }

    public PsmDTOBuilder setModificationConnectionsB(String modificationConnectionsB) {
        this.modificationConnectionsB = modificationConnectionsB;
        return this;
    }

    public PsmDTOBuilder setSpectrumFilePath(String spectrumFilePath) {
        this.spectrumFilePath = spectrumFilePath;
        return this;
    }

    public PsmDTOBuilder setCrosslinkReagent(String crosslinkReagent) {
        this.crosslinkReagent = crosslinkReagent;
        return this;
    }

    public PsmDTOBuilder setScanNumber(int scanNumber) {
        this.scanNumber = scanNumber;
        return this;
    }

    public PsmDTOBuilder setRetentionTime(BigDecimal retentionTime) {
        this.retentionTime = retentionTime;
        return this;
    }

    public PsmDTOBuilder setPrecursorMz(BigDecimal precursorMz) {
        this.precursorMz = precursorMz;
        return this;
    }

    public PsmDTOBuilder setPrecursorChargeState(int precursorChargeState) {
        this.precursorChargeState = precursorChargeState;
        return this;
    }

    public PsmDTOBuilder setReporterScore(BigDecimal reporterScore) {
        this.reporterScore = reporterScore;
        return this;
    }

    public PsmDTOBuilder setScore(BigDecimal score) {
        this.score = score;
        return this;
    }

    public PsmDTOBuilder setScoreDifference(BigDecimal scoreDifference) {
        this.scoreDifference = scoreDifference;
        return this;
    }

    public PsmDTOBuilder setDeltaMass(BigDecimal deltaMass) {
        this.deltaMass = deltaMass;
        return this;
    }

    public PsmDTOBuilder setFractionIonsMatchedBSeries(BigDecimal fractionIonsMatchedBSeries) {
        this.fractionIonsMatchedBSeries = fractionIonsMatchedBSeries;
        return this;
    }

    public PsmDTOBuilder setFractionIonsMatchedYSeries(BigDecimal fractionIonsMatchedYSeries) {
        this.fractionIonsMatchedYSeries = fractionIonsMatchedYSeries;
        return this;
    }

    public PsmDTOBuilder setFragmentationEfficiency(BigDecimal fragmentationEfficiency) {
        this.fragmentationEfficiency = fragmentationEfficiency;
        return this;
    }

    public PsmDTOBuilder setPercolatorSVMScore(BigDecimal percolatorSVMScore) {
        this.percolatorSVMScore = percolatorSVMScore;
        return this;
    }

    public PsmDTOBuilder setPercolatorPValue(BigDecimal percolatorPValue) {
        this.percolatorPValue = percolatorPValue;
        return this;
    }

    public PsmDTOBuilder setPercolatorQValue(BigDecimal percolatorQValue) {
        this.percolatorQValue = percolatorQValue;
        return this;
    }

    public PsmDTOBuilder setPercolatorPEP(BigDecimal percolatorPEP) {
        this.percolatorPEP = percolatorPEP;
        return this;
    }

    public PsmDTOBuilder setValidatorQValue(BigDecimal validatorQValue) {
        this.validatorQValue = validatorQValue;
        return this;
    }

    public PsmDTO createPsmDTO() {
        return new PsmDTO(id, sequenceA, peptidePositionA, numberMatchesA, ptmScoreA, sequenceCoverageA, modificationConnectionsA, sequenceB, peptidePositionB, numberMatchesB, ptmScoreB, sequenceCoverageB, modificationConnectionsB, spectrumFilePath, crosslinkReagent, scanNumber, retentionTime, precursorMz, precursorChargeState, reporterScore, score, scoreDifference, deltaMass, fractionIonsMatchedBSeries, fractionIonsMatchedYSeries, fragmentationEfficiency, percolatorSVMScore, percolatorPValue, percolatorQValue, percolatorPEP, validatorQValue);
    }
}