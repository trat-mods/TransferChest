package net.enderbox.mod.initializer;

import net.enderbox.mod.block.EnderBoxBlock;
import net.enderbox.mod.entity.EnderBoxBlockEntity;
import net.enderbox.mod.loader.EBLoader;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public final class EBEntities
{
    public static BlockEntityType<EnderBoxBlockEntity> ENDER_BOX_BLOCK_ENTITY;
    
    public static void initialize()
    {
        ENDER_BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EBLoader.MOD_ID + ":" + EnderBoxBlock.ID.getPath(), BlockEntityType.Builder.create(EnderBoxBlockEntity::new, EBBlocks.ENDER_BOX_BLOCK).build(null));
    }
}
