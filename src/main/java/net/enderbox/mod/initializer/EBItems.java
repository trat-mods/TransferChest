package net.enderbox.mod.initializer;

import net.enderbox.mod.block.EnderBoxBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public final class EBItems
{
    public static Item ENDER_BOX_ITEM;
    
    public static void initialize()
    {
        ENDER_BOX_ITEM = Registry.register(Registry.ITEM, EnderBoxBlock.ID, new BlockItem(EBBlocks.ENDER_BOX_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }
}
