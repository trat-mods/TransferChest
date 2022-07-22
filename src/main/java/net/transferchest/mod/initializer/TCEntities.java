package net.transferchest.mod.initializer;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.transferchest.mod.block.TransferChestBlock;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.loader.TCLoader;

public final class TCEntities {
    public static BlockEntityType<TransferChestBlockEntity> TRANSFER_CHEST_BLOCK_ENTITY;

    public static void initialize() {
        TRANSFER_CHEST_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, TCLoader.MOD_ID + ":" + TransferChestBlock.ID.getPath(),
                                                        FabricBlockEntityTypeBuilder.create(TransferChestBlockEntity::new, TCBlocks.TRANSFER_CHEST_BLOCK).build(null));
    }
}
