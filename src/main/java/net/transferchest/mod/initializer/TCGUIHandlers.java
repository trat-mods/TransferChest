package net.transferchest.mod.initializer;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.transferchest.mod.block.TransferChestBlock;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;

public final class TCGUIHandlers {
    public static ScreenHandlerType TRANSFER_CHEST_GUI_HANDLER;

    public static void initialize() {
        TRANSFER_CHEST_GUI_HANDLER = ScreenHandlerRegistry.registerExtended(TransferChestBlock.ID,
                                                                            (syncId, inventory, buf) -> new TransferChestGUIHandler(syncId, inventory, ScreenHandlerContext.create(inventory.player.getWorld(), buf.readBlockPos())));
    }
}
