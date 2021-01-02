package net.transferchest.mod.loader;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.transferchest.mod.initializer.TCScreens;
import net.transferchest.mod.network.packet.TransferChestWatchersS2CPacket;

;

public final class TCClientLoader implements ClientModInitializer
{
    @Override public void onInitializeClient()
    {
        TCScreens.initialize();
        ClientSidePacketRegistry.INSTANCE.register(TransferChestWatchersS2CPacket.PACKET_ID, (ctx, buf) -> TransferChestWatchersS2CPacket.read(buf).onReceive(ctx));
    }
}
