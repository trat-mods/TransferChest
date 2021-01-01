package net.transferchest.mod.loader;

import net.transferchest.mod.initializer.*;
import net.fabricmc.api.ModInitializer;

public final class TCLoader implements ModInitializer
{
    public static final String MOD_ID = "transferchestmod";
    
    
    @Override public void onInitialize()
    {
        TCEntities.initialize();
        TCBlocks.initialize();
        TCItems.initialize();
        TCGUIHandlers.initialize();
        TCSounds.initialize();
    }
}
