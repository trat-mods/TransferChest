package net.transferchest.mod.initializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.transferchest.mod.block.TransferChestBlock;

public final class TCItems {
    public static Item TRANSFER_CHEST_ITEM;

    public static void initialize() {
        TRANSFER_CHEST_ITEM = Registry.register(Registries.ITEM, TransferChestBlock.ID, new BlockItem(TCBlocks.TRANSFER_CHEST_BLOCK, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> content.add(TRANSFER_CHEST_ITEM));
    }

}
