package com.shinobi.client.render;

import com.shinobi.ShinobiMod;
import com.shinobi.entity.ThrownKunaiEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class KunaiRenderer extends ArrowRenderer<ThrownKunaiEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ShinobiMod.MOD_ID, "textures/entity/kunai.png");

    public KunaiRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownKunaiEntity entity) {
        return TEXTURE;
    }
}
