package net.transferchest.mod.initializer;

import net.minecraft.util.registry.Registry;
import net.transferchest.mod.block.TransferChestBlock;


public final class TCBlocks
{
    public static final TransferChestBlock TRANSFER_CHEST_BLOCK = new TransferChestBlock();
    
    public static void initialize()
    {
        Registry.register(Registry.BLOCK, TransferChestBlock.ID, TRANSFER_CHEST_BLOCK);
    }
}
