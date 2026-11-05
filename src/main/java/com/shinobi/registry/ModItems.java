package com.shinobi.registry;

import com.shinobi.ShinobiMod;
import com.shinobi.item.ModArmorMaterials;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ShinobiMod.MOD_ID);

    // ---- Ninja tools & weapons ----
    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai",
            () -> new com.shinobi.item.KunaiItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SHURIKEN = ITEMS.register("shuriken",
            () -> new com.shinobi.item.ShurikenItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> NINJATO = ITEMS.register("ninjato",
            () -> new SwordItem(Tiers.IRON, 4, -2.0F, new Item.Properties()));

    public static final RegistryObject<Item> CHAKRA_PILL = ITEMS.register("chakra_pill",
            () -> new com.shinobi.item.ChakraPillItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).alwaysEat().build())
                    .stacksTo(16)));

    public static final RegistryObject<Item> HEADBAND = ITEMS.register("ninja_headband",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // ---- Armor: five hidden villages ----
    public static final RegistryObject<Item> KONOHA_HELMET = armor("konoha", ModArmorMaterials.KONOHA, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> KONOHA_CHEST = armor("konoha", ModArmorMaterials.KONOHA, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> KONOHA_LEGS = armor("konoha", ModArmorMaterials.KONOHA, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> KONOHA_BOOTS = armor("konoha", ModArmorMaterials.KONOHA, ArmorItem.Type.BOOTS);

    public static final RegistryObject<Item> SUNA_HELMET = armor("suna", ModArmorMaterials.SUNA, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SUNA_CHEST = armor("suna", ModArmorMaterials.SUNA, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SUNA_LEGS = armor("suna", ModArmorMaterials.SUNA, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SUNA_BOOTS = armor("suna", ModArmorMaterials.SUNA, ArmorItem.Type.BOOTS);

    public static final RegistryObject<Item> KIRI_HELMET = armor("kiri", ModArmorMaterials.KIRI, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> KIRI_CHEST = armor("kiri", ModArmorMaterials.KIRI, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> KIRI_LEGS = armor("kiri", ModArmorMaterials.KIRI, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> KIRI_BOOTS = armor("kiri", ModArmorMaterials.KIRI, ArmorItem.Type.BOOTS);

    public static final RegistryObject<Item> IWA_HELMET = armor("iwa", ModArmorMaterials.IWA, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> IWA_CHEST = armor("iwa", ModArmorMaterials.IWA, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> IWA_LEGS = armor("iwa", ModArmorMaterials.IWA, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> IWA_BOOTS = armor("iwa", ModArmorMaterials.IWA, ArmorItem.Type.BOOTS);

    public static final RegistryObject<Item> KUMO_HELMET = armor("kumo", ModArmorMaterials.KUMO, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> KUMO_CHEST = armor("kumo", ModArmorMaterials.KUMO, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> KUMO_LEGS = armor("kumo", ModArmorMaterials.KUMO, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> KUMO_BOOTS = armor("kumo", ModArmorMaterials.KUMO, ArmorItem.Type.BOOTS);

    // ---- Spawn egg ----
    public static final RegistryObject<Item> NINJA_VILLAGER_SPAWN_EGG = ITEMS.register("ninja_villager_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.NINJA_VILLAGER, 0x3a3a3a, 0xc0392b, new Item.Properties()));

    private static RegistryObject<Item> armor(String village, ArmorMaterial material, ArmorItem.Type type) {
        int durability = type.getDurability(15);
        return ITEMS.register(village + "_" + type.getName(),
                () -> new ArmorItem(material, type, new Item.Properties().durability(durability)));
    }
}
