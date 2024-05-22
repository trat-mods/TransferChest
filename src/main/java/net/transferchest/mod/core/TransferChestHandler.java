package net.transferchest.mod.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.transferchest.mod.api.ModLogger;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.network.NetworkHandler;
import net.transferchest.mod.network.packet.TransferChestPacketPayload;

import java.util.ArrayList;

public final class TransferChestHandler {
    private static TransferChestInventory inventory;
    private static ArrayList<TransferChestGUIHandler> openGUIs;

    public static void initialize() {
        inventory = new TransferChestInventory(TransferChestBlockEntity.INVENTORY_SIZE);
        openGUIs = new ArrayList<>();
    }

    public static DefaultedList<ItemStack> getTransferChestInventory() {
        return inventory.getItems();
    }


    public static SerializableInventory getSerializableInventory() {
        return new SerializableInventory(inventory.getItems());
    }

    public static void openGUI(World world, TransferChestGUIHandler handler) {
        openGUIs.add(handler);
        notifyClients(world);
    }

    public static void closeGUI(World world, TransferChestGUIHandler handler) {
        openGUIs.remove(handler);
        notifyClients(world);
    }

    private static void notifyClients(World world) {
        String[] names = new String[openGUIs.size()];
        for (int i = 0; i < openGUIs.size(); i++) {
            names[i] = openGUIs.get(i).getOwnerName().getString();
        }
        NetworkHandler.sendToAll(new TransferChestPacketPayload(names), world.getServer().getPlayerManager());
    }


    public static void printStatus() {
        ModLogger.DEFAULT_CHANNEL.logInfo(inventory.toString());
    }

    public static void buildInventory(SerializableInventory serializableInventory) {
        int length = serializableInventory.size();
        for (int i = 0; i < length; i++) {
            ItemStackWrapper current = serializableInventory.getInventory()[i];
            inventory.getItems().set(i, new ItemStack(Item.byRawId(current.getID()), current.getCount()));
        }
    }
}
