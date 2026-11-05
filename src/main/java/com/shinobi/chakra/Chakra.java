package com.shinobi.chakra;

public class Chakra implements IChakra {
    private int chakra;
    private int maxChakra = 100;

    @Override
    public int getChakra() {
        return chakra;
    }

    @Override
    public int getMaxChakra() {
        return maxChakra;
    }

    @Override
    public void setChakra(int value) {
        this.chakra = Math.max(0, Math.min(maxChakra, value));
    }

    @Override
    public void copyFrom(IChakra source) {
        this.chakra = source.getChakra();
        this.maxChakra = source.getMaxChakra();
    }
}
