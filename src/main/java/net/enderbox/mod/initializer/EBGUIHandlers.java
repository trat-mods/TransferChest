package net.enderbox.mod.initializer;

import net.enderbox.mod.block.EnderBoxBlock;
import net.enderbox.mod.gui.handler.EnderBoxGUIHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public final class EBGUIHandlers
{
    public static ScreenHandlerType ENDER_BOX_GUI_HANDLER;
    
    public static void initialize()
    {
        ENDER_BOX_GUI_HANDLER = ScreenHandlerRegistry.registerExtended(EnderBoxBlock.ID, (syncId, inventory, buf) -> new EnderBoxGUIHandler(syncId, inventory, ScreenHandlerContext.create(inventory.player.world, buf.readBlockPos())));
    }
}
