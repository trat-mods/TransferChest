package net.transferchest.mod.core;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.transferchest.mod.api.ImplementedInventory;

public class TransferChestInventory implements ImplementedInventory
{
    private DefaultedList<ItemStack> items;
    
    public TransferChestInventory(int size)
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
