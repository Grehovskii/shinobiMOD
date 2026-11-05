package com.shinobi.entity.villager;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;

/**
 * A trading ninja NPC, spawned (e.g. via its spawn egg) already set to the
 * Weaponsmith profession and a mid trading level, so it has ninja-gear
 * trades (added in {@code ModVillagers}) from the moment it appears,
 * without relying on it finding and claiming a job-site block first.
 *
 * It reuses the vanilla villager model and renderer, so its appearance
 * follows vanilla's biome-based skin system automatically (desert, snow,
 * plains, etc.) — a cheap, reliable stand-in for "one look per village"
 * without shipping five custom models.
 */
public class NinjaVillagerEntity extends Villager {

    public NinjaVillagerEntity(EntityType<? extends Villager> type, Level level) {
        super(type, level, VillagerType.PLAINS);
        this.setVillagerData(this.getVillagerData()
                .setProfession(VillagerProfession.WEAPONSMITH)
                .setLevel(3));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Villager.createAttributes();
    }
}
