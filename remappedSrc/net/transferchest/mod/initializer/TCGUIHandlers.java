package net.transferchest.mod.initializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.transferchest.mod.block.TransferChestBlock;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;


public final class TCGUIHandlers {
    public static ScreenHandlerType<TransferChestGUIHandler> TRANSFER_CHEST_GUI_HANDLER;

    public static void initialize() {
        TRANSFER_CHEST_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, TransferChestBlock.ID,
                                                       new ScreenHandlerType<>((syncId, inventory) -> new TransferChestGUIHandler(syncId, inventory, ScreenHandlerContext.EMPTY), FeatureFlags.VANILLA_FEATURES));
    }


}
