package net.transferchest.mod.loader;

import net.fabricmc.api.ModInitializer;
import net.transferchest.mod.initializer.*;

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
