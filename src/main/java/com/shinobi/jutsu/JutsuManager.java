package com.shinobi.jutsu;

import com.shinobi.chakra.ChakraProvider;
import com.shinobi.entity.jutsu.EarthSpikeEntity;
import com.shinobi.entity.jutsu.FireballJutsuEntity;
import com.shinobi.entity.jutsu.WaterJutsuEntity;
import com.shinobi.entity.jutsu.WindBladeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Server-side handler for jutsu casting. Each element costs chakra and has a
 * short effect; all are intentionally simple so they're easy to re-balance
 * or extend with real animations/particles later.
 */
public class JutsuManager {

    private static final int COST = 15;

    public static void cast(ServerPlayer player, Element element) {
        player.getCapability(ChakraProvider.CHAKRA_CAP).ifPresent(chakra -> {
            if (!chakra.spend(COST)) {
                player.displayClientMessage(net.minecraft.network.chat.Component.literal("Недостаточно чакры!"), true);
                return;
            }

            ServerLevel level = player.serverLevel();
            switch (element) {
                case FIRE -> castFire(player, level);
                case WATER -> castWater(player, level);
                case EARTH -> castEarth(player, level);
                case WIND -> castWind(player, level);
                case LIGHTNING -> castLightning(player, level);
            }
        });
    }

    private static void castFire(ServerPlayer player, ServerLevel level) {
        level.playSound(null, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, 0.8F);
        FireballJutsuEntity fireball = new FireballJutsuEntity(level, player);
        fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        level.addFreshEntity(fireball);
    }

    private static void castWater(ServerPlayer player, ServerLevel level) {
        level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);
        WaterJutsuEntity water = new WaterJutsuEntity(level, player);
        water.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.3F, 1.0F);
        level.addFreshEntity(water);
    }

    private static void castWind(ServerPlayer player, ServerLevel level) {
        level.playSound(null, player.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_3, SoundSource.PLAYERS, 1.0F, 1.4F);
        WindBladeEntity wind = new WindBladeEntity(level, player);
        wind.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.2F, 0.5F);
        level.addFreshEntity(wind);
    }

    private static void castEarth(ServerPlayer player, ServerLevel level) {
        level.playSound(null, player.blockPosition(), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 1.0F, 0.6F);
        Vec3 look = player.getLookAngle();
        BlockPos origin = player.blockPosition();
        for (int i = 1; i <= 4; i++) {
            BlockPos pos = origin.offset((int) Math.round(look.x * i), 0, (int) Math.round(look.z * i));
            EarthSpikeEntity spike = new EarthSpikeEntity(level, pos);
            level.addFreshEntity(spike);
        }
    }

    private static void castLightning(ServerPlayer player, ServerLevel level) {
        HitResult hit = player.pick(20.0D, 0.0F, false);
        BlockPos target;
        if (hit.getType() == HitResult.Type.BLOCK) {
            target = BlockPos.containing(hit.getLocation());
        } else {
            Vec3 look = player.getLookAngle().scale(15).add(player.position());
            target = BlockPos.containing(look);
        }
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt != null) {
            bolt.moveTo(Vec3.atBottomCenterOf(target));
            bolt.setVisualOnly(false);
            bolt.setCause(player);
            level.addFreshEntity(bolt);
        }
    }
}
