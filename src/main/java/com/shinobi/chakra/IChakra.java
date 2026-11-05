package com.shinobi.chakra;

public interface IChakra {
    int getChakra();
    int getMaxChakra();
    void setChakra(int value);

    default void add(int amount) {
        setChakra(Math.min(getMaxChakra(), getChakra() + amount));
    }

    /** Tries to spend chakra; returns true and deducts it if there was enough. */
    default boolean spend(int amount) {
        if (getChakra() >= amount) {
            setChakra(getChakra() - amount);
            return true;
        }
        return false;
    }

    void copyFrom(IChakra source);
}
