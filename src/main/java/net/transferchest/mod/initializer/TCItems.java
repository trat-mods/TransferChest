package net.transferchest.mod.initializer;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.transferchest.mod.block.TransferChestBlock;

public final class TCItems
{
    public static Item TRANSFER_CHEST_ITEM;
    
    public static void initialize()
    {
        TRANSFER_CHEST_ITEM = Registry.register(Registry.ITEM, TransferChestBlock.ID, new BlockItem(TCBlocks.TRANSFER_CHEST_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }
}
