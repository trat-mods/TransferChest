package net.transferchest.mod.network.packet;

import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public abstract class CustomPacket
{
    
    public CustomPacket()
    {
    }
    
    public abstract void sendTo(PlayerEntity player);
    
    protected abstract void onReceive(PacketContext ctx);
    
    public abstract void write(PacketByteBuf buf);
}
