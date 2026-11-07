package com.shinobi.item;

import com.shinobi.ShinobiMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ArmorItem;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * Five lightweight ninja armor materials, one per hidden village.
 * Stats sit between leather and iron: light, with a small toughness bonus
 * to reflect agile shinobi gear rather than heavy plate.
 */
public enum ModArmorMaterials implements ArmorMaterial {
    KONOHA("konoha", 18, map(2, 5, 6, 2), 11, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, () -> Ingredient.of(net.minecraft.world.item.Items.LEATHER)),
    SUNA("suna", 20, map(2, 5, 6, 2), 11, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, () -> Ingredient.of(net.minecraft.world.item.Items.SAND)),
    KIRI("kiri", 20, map(3, 6, 7, 3), 9, SoundEvents.ARMOR_EQUIP_CHAIN, 1.5F, 0.0F, () -> Ingredient.of(net.minecraft.world.item.Items.PRISMARINE_SHARD)),
    IWA("iwa", 24, map(3, 6, 8, 3), 9, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> Ingredient.of(net.minecraft.world.item.Items.IRON_INGOT)),
    KUMO("kumo", 19, map(2, 5, 6, 3), 13, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.1F, () -> Ingredient.of(net.minecraft.world.item.Items.FEATHER));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = new EnumMap<>(ArmorItem.Type.class);
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> defense;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> defense,
                       int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance,
                       Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defense = defense;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    private static EnumMap<ArmorItem.Type, Integer> map(int boots, int legs, int chest, int helmet) {
        EnumMap<ArmorItem.Type, Integer> m = new EnumMap<>(ArmorItem.Type.class);
        m.put(ArmorItem.Type.BOOTS, boots);
        m.put(ArmorItem.Type.LEGGINGS, legs);
        m.put(ArmorItem.Type.CHESTPLATE, chest);
        m.put(ArmorItem.Type.HELMET, helmet);
        return m;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 13 * durabilityMultiplier;
            case LEGGINGS -> 15 * durabilityMultiplier;
            case CHESTPLATE -> 16 * durabilityMultiplier;
            case HELMET -> 11 * durabilityMultiplier;
        };
    }

    /** Same per-slot base durability used above, exposed for {@code ModItems} to size its {@code ItemStack}s with. */
    public static int baseDurability(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 13;
            case LEGGINGS -> 15;
            case CHESTPLATE -> 16;
            case HELMET -> 11;
        };
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return defense.getOrDefault(type, 0);
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return ShinobiMod.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
