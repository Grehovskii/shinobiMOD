package com.shinobi.registry;

import com.shinobi.ShinobiMod;
import com.shinobi.entity.ThrownKunaiEntity;
import com.shinobi.entity.ThrownShurikenEntity;
import com.shinobi.entity.jutsu.EarthSpikeEntity;
import com.shinobi.entity.jutsu.FireballJutsuEntity;
import com.shinobi.entity.jutsu.WaterJutsuEntity;
import com.shinobi.entity.jutsu.WindBladeEntity;
import com.shinobi.entity.villager.NinjaVillagerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ShinobiMod.MOD_ID);

    public static final RegistryObject<EntityType<ThrownKunaiEntity>> THROWN_KUNAI = ENTITY_TYPES.register("thrown_kunai",
            () -> EntityType.Builder.<ThrownKunaiEntity>of(ThrownKunaiEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20).build("thrown_kunai"));

    public static final RegistryObject<EntityType<ThrownShurikenEntity>> THROWN_SHURIKEN = ENTITY_TYPES.register("thrown_shuriken",
            () -> EntityType.Builder.<ThrownShurikenEntity>of(ThrownShurikenEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(20).build("thrown_shuriken"));

    public static final RegistryObject<EntityType<FireballJutsuEntity>> FIREBALL_JUTSU = ENTITY_TYPES.register("fireball_jutsu",
            () -> EntityType.Builder.<FireballJutsuEntity>of(FireballJutsuEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F).clientTrackingRange(10).updateInterval(10).build("fireball_jutsu"));

    public static final RegistryObject<EntityType<WaterJutsuEntity>> WATER_JUTSU = ENTITY_TYPES.register("water_jutsu",
            () -> EntityType.Builder.<WaterJutsuEntity>of(WaterJutsuEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F).clientTrackingRange(10).updateInterval(10).build("water_jutsu"));

    public static final RegistryObject<EntityType<WindBladeEntity>> WIND_JUTSU = ENTITY_TYPES.register("wind_jutsu",
            () -> EntityType.Builder.<WindBladeEntity>of(WindBladeEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.2F).clientTrackingRange(10).updateInterval(10).build("wind_jutsu"));

    public static final RegistryObject<EntityType<EarthSpikeEntity>> EARTH_SPIKE = ENTITY_TYPES.register("earth_spike",
            () -> EntityType.Builder.<EarthSpikeEntity>of(EarthSpikeEntity::new, MobCategory.MISC)
                    .sized(0.8F, 1.5F).clientTrackingRange(10).updateInterval(10).build("earth_spike"));

    public static final RegistryObject<EntityType<NinjaVillagerEntity>> NINJA_VILLAGER = ENTITY_TYPES.register("ninja_villager",
            () -> EntityType.Builder.of(NinjaVillagerEntity::new, MobCategory.MISC)
                    .sized(0.6F, 1.95F).clientTrackingRange(10).build("ninja_villager"));
}
