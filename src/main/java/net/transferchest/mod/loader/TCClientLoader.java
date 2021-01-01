package net.transferchest.mod.loader;

import net.transferchest.mod.initializer.EBScreens;
import net.fabricmc.api.ClientModInitializer;

public final class TCClientLoader implements ClientModInitializer
{
    @Override public void onInitializeClient()
    {
        EBScreens.initialize();
    }
}
