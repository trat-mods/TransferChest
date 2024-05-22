package net.transferchest.mod.network;


import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.transferchest.mod.network.packet.TransferChestPacketPayload;

import java.util.List;

public class NetworkHandler {
    public static void sendToAll(TransferChestPacketPayload packet, PlayerManager manager) {
        List<ServerPlayerEntity> targets = manager.getPlayerList();
        for (ServerPlayerEntity target : targets) {
            packet.sendTo(target);
        }
    }
}
