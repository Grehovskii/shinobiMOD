package com.shinobi.registry;

import com.shinobi.ShinobiMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** A handful of decorative blocks for building the five hidden villages. */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ShinobiMod.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ShinobiMod.MOD_ID);

    public static final RegistryObject<Block> KONOHA_BRICKS = block("konoha_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.0F)));

    public static final RegistryObject<Block> SUNA_SANDSTONE = block("suna_sandstone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(1.5F)));

    public static final RegistryObject<Block> KIRI_STONE = block("kiri_mist_stone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(2.5F)));

    public static final RegistryObject<Block> IWA_ROCK = block("iwa_cut_rock",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3.0F)));

    public static final RegistryObject<Block> KUMO_CLOUDSTONE = block("kumo_cloudstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(1.0F)));

    private static RegistryObject<Block> block(String name, java.util.function.Supplier<Block> blockSupplier) {
        RegistryObject<Block> block = BLOCKS.register(name, blockSupplier);
        BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
