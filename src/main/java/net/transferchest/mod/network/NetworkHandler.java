package net.transferchest.mod.network;


import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.transferchest.mod.network.packet.CustomPacket;

import java.util.List;

public class NetworkHandler
{
    public static void sendToAll(CustomPacket packet, PlayerManager manager)
    {
        List<ServerPlayerEntity> targets = manager.getPlayerList();
        for(int i = 0; i < targets.size(); ++i)
        {
            packet.sendTo(targets.get(i));
        }
    }
}
