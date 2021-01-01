package net.enderbox.mod.initializer;

import net.enderbox.mod.gui.handler.EnderBoxGUIHandler;
import net.enderbox.mod.gui.screen.EnderBoxScreen;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public final class EBScreens
{
    public static void initialize()
    {
        ScreenRegistry.<EnderBoxGUIHandler, EnderBoxScreen>register(EBGUIHandlers.ENDER_BOX_GUI_HANDLER, (gui, inventory, title) -> new EnderBoxScreen(gui, inventory.player, title));
    }
}
