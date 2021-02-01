package net.transferchest.mod.loader;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.transferchest.mod.initializer.TCScreens;
import net.transferchest.mod.network.packet.TransferChestWatchersS2CPacket;

;

public final class TCClientLoader implements ClientModInitializer
{
    @Override public void onInitializeClient()
    {
        TCScreens.initialize();
        ClientPlayNetworking.registerGlobalReceiver(TransferChestWatchersS2CPacket.PACKET_ID, ((client, handler, buf, sender) -> TransferChestWatchersS2CPacket.read(buf).onReceive(client)));
        //ClientSidePacketRegistry.INSTANCE.register(TransferChestWatchersS2CPacket.PACKET_ID, (ctx, buf) -> );
    }
}
