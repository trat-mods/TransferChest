package net.enderbox.mod.core;

import net.enderbox.mod.api.ModLogger;
import net.enderbox.mod.entity.EnderBoxBlockEntity;
import net.enderbox.mod.loader.EBLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;

public final class EnderBoxHandler
{
    private static EnderBoxInventory inventory;
    
    private static ArrayList<Listener> listeners;
    public static void initialize()
    {
        inventory = new EnderBoxInventory(EnderBoxBlockEntity.INVENTORY_SIZE);
        listeners = new ArrayList<>();
    }
    public static EnderBoxInventory getInventory()
    {
        return inventory;
    }
    public static void addListener(EnderBoxBlockEntity entity, int syncId)
    {
        listeners.add(new Listener(entity, syncId));
        System.out.println("Listener added, number: "+ listeners.size());

    }
    public static boolean removeListener(EnderBoxBlockEntity entity, int syncId)
    {
        boolean res= listeners.remove(entity);
        if(res)System.out.println("Listener removed, number: "+ listeners.size());
        return res;
    }
    public static DefaultedList<ItemStack> getEnderBoxInventory()
    {
        return inventory.getItems();
    }
    
    public static void enderBoxInventoryChangedCallback(int slot, ItemStack newStack)
    {
        if(!ItemStack.areEqual(inventory.getStack(slot), newStack))
        {
            inventory.getItems().set(slot, newStack);
            System.out.println("Slot modified, "+slot+" "+newStack.toString());
        }
    }
    
    public static void clear()
    {
        inventory.clear();
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
        System.out.println("Building inventory");
        int length = serializableInventory.size();
        for(int i = 0; i < length; i++)
        {
            ItemStackWrapper current = serializableInventory.getInventory()[i];
            inventory.getItems().set(i, new ItemStack(Item.byRawId(current.getID()), current.getCount()));
        }
    }
    
    public static class Listener
    {
        public int syncId;
        public EnderBoxBlockEntity entity;
        public Listener(EnderBoxBlockEntity entity, int syncId)
        {
            this.syncId = syncId;
            this.entity = entity;
        }
    }
}
