package net.transferchest.mod.initializer;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.gui.screen.TransferChestScreen;

public final class TCScreens {
    public static void initialize() {
        ScreenRegistry.<TransferChestGUIHandler, TransferChestScreen>register(TCGUIHandlers.TRANSFER_CHEST_GUI_HANDLER, (gui, inventory, title) -> new TransferChestScreen(gui, inventory.player, title));
    }
}
