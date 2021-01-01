package net.enderbox.mod.core;

import net.enderbox.mod.api.ImplementedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class EnderBoxInventory implements ImplementedInventory
{
    private DefaultedList<ItemStack> items;
    
    public EnderBoxInventory(int size)
    {
        items = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }
    
    @Override public DefaultedList<ItemStack> getItems()
    {
        return items;
    }
    
    @Override public String toString()
    {
        String ret = "Inventory: ";
        for(int i = 0; i < items.size(); i++)
        {
            ret += items.get(i).toString();
            if(i < items.size() - 1)
            {
                ret += ", ";
            }
        }
        return ret;
    }
}
