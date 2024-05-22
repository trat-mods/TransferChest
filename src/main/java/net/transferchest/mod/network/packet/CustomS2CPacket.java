package net.transferchest.mod.network.packet;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;

public abstract class CustomS2CPacket implements CustomPayload {

    public CustomS2CPacket() {
    }

    public abstract void sendTo(PlayerEntity player);

    protected abstract void onReceive(MinecraftClient ctx);

    public abstract void write(PacketByteBuf buf);
}
