package com.shinobi.chakra;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChakraProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IChakra> CHAKRA_CAP = CapabilityManager.get(new CapabilityToken<>() {});

    private final Chakra backend = new Chakra();
    private final LazyOptional<IChakra> optional = LazyOptional.of(() -> backend);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CHAKRA_CAP ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("chakra", backend.getChakra());
        tag.putInt("maxChakra", backend.getMaxChakra());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        backend.setChakra(tag.getInt("chakra"));
    }

    public IChakra getBackend() {
        return backend;
    }
}
