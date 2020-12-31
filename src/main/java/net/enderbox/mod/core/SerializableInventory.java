package net.enderbox.mod.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.Serializable;

public class SerializableInventory implements Serializable
{
    private ItemStackWrapper[] inventory;
    
    public int size()
    {
        return inventory.length;
    }
    
    public SerializableInventory(ItemStack[] stacks)
    {
        int length = stacks.length;
        inventory = new ItemStackWrapper[length];
        for(int i = 0; i < length; i++)
        {
            ItemStack stack = stacks[i];
            inventory[i] = new ItemStackWrapper(Item.getRawId(stack.getItem()), stack.getCount());
        }
    }
    
    public ItemStackWrapper[] getInventory()
    {
        return inventory;
    }
}
