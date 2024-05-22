package net.transferchest.mod.loader;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.transferchest.mod.initializer.TCScreens;
import net.transferchest.mod.network.packet.TransferChestPacketPayload;

;

public final class TCClientLoader implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TCScreens.initialize();
        PayloadTypeRegistry.playS2C().register(TransferChestPacketPayload.ID, TransferChestPacketPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(TransferChestPacketPayload.ID, ((payload, context) -> context.client().execute(() -> {
            payload.onReceive(context.client());
        })));
        //ClientSidePacketRegistry.INSTANCE.register(TransferChestWatchersS2CPacket.PACKET_ID, (ctx, buf) -> );
    }
}
