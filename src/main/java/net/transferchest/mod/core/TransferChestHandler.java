package net.transferchest.mod.core;

import net.transferchest.mod.api.ModLogger;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.loader.TCLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public final class TransferChestHandler
{
    private static TransferChestInventory inventory;
    
    public static void initialize()
    {
        inventory = new TransferChestInventory(TransferChestBlockEntity.INVENTORY_SIZE);
    }
    
    public static DefaultedList<ItemStack> getEnderBoxInventory()
    {
        return inventory.getItems();
    }
    
    
    public static SerializableInventory getSerializableInventory()
    {
        return new SerializableInventory(inventory.getItems());
    }
    
    public static void printStatus()
    {
        new ModLogger(TCLoader.MOD_ID).logInfo(inventory.toString());
    }
    
    public static void buildInventory(SerializableInventory serializableInventory)
    {
        int length = serializableInventory.size();
        for(int i = 0; i < length; i++)
        {
            ItemStackWrapper current = serializableInventory.getInventory()[i];
            inventory.getItems().set(i, new ItemStack(Item.byRawId(current.getID()), current.getCount()));
        }
    }
}
