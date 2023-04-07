package net.transferchest.mod.initializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.transferchest.mod.block.TransferChestBlock;


public final class TCBlocks {
    public static final TransferChestBlock TRANSFER_CHEST_BLOCK = new TransferChestBlock();

    public static void initialize() {
        Registry.register(Registries.BLOCK, TransferChestBlock.ID, TRANSFER_CHEST_BLOCK);
    }
}
