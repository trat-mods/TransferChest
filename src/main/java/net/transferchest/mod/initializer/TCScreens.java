package net.transferchest.mod.initializer;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.gui.screen.TransferChestScreen;

public final class TCScreens {
    public static void initialize() {
        HandledScreens.<TransferChestGUIHandler, TransferChestScreen>register(TCGUIHandlers.TRANSFER_CHEST_GUI_HANDLER, (gui, inventory, title) -> new TransferChestScreen(gui, inventory.player, title));
    }
}
