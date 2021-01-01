package net.enderbox.mod.loader;

import net.enderbox.mod.initializer.EBBlocks;
import net.enderbox.mod.initializer.EBEntities;
import net.enderbox.mod.initializer.EBGUIHandlers;
import net.enderbox.mod.initializer.EBItems;
import net.fabricmc.api.ModInitializer;

public final class EBLoader implements ModInitializer
{
    public static final String MOD_ID = "enderboxmod";
    
    
    @Override public void onInitialize()
    {
        EBEntities.initialize();
        EBBlocks.initialize();
        EBItems.initialize();
        EBGUIHandlers.initialize();
    }
}
