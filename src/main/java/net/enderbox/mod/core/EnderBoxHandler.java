package net.enderbox.mod.core;

import net.enderbox.mod.api.ModLogger;
import net.enderbox.mod.entity.EnderBoxBlockEntity;
import net.enderbox.mod.loader.EBLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public final class EnderBoxHandler
{
    private static EnderBoxInventory inventory;
    
    public static void initialize()
    {
        inventory = new EnderBoxInventory(EnderBoxBlockEntity.INVENTORY_SIZE);
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
        new ModLogger(EBLoader.MOD_ID).logInfo(inventory.toString());
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
