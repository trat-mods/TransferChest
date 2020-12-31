package net.enderbox.mod.core;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class EnderBoxHandler
{
    private static ItemStack[] stacks = null;
    
    public static SimpleInventory getEnderBoxInventory()
    {
        if(stacks == null) return null;
        int length = stacks.length;
        SimpleInventory res = new SimpleInventory(length);
        for(int i = 0; i < length; i++)
        {
            res.setStack(i, stacks[i]);
        }
        return res;
    }
    
    public static void enderBoxInventoryChangedCallback(int slot, ItemStack newStack)
    {
        stacks[slot] = newStack;
    }
    
    public static SerializableInventory getSerializableInventory()
    {
        if(stacks == null) return null;
        return new SerializableInventory(stacks);
    }
    
    public static void buildInventory(SerializableInventory serializableInventory)
    {
        int length = serializableInventory.size();
        stacks = new ItemStack[length];
        for(int i = 0; i < length; i++)
        {
            ItemStackWrapper current = serializableInventory.getInventory()[i];
            stacks[i] = new ItemStack(Item.byRawId(current.getID()), current.getCount());
        }
    }
}
