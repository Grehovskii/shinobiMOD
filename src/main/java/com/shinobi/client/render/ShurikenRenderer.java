package com.shinobi.client.render;

import com.shinobi.ShinobiMod;
import com.shinobi.entity.ThrownShurikenEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ShurikenRenderer extends ArrowRenderer<ThrownShurikenEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ShinobiMod.MOD_ID, "textures/entity/shuriken.png");

    public ShurikenRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownShurikenEntity entity) {
        return TEXTURE;
    }
}
