package net.enderbox.mod.loader;

import net.enderbox.mod.initializer.EBScreens;
import net.fabricmc.api.ClientModInitializer;

public final class EBClientLoader implements ClientModInitializer
{
    @Override public void onInitializeClient()
    {
        EBScreens.initialize();
    }
}
