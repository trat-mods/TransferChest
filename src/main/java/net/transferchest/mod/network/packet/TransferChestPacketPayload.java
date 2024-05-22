package net.transferchest.mod.network.packet;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;

public record TransferChestPacketPayload(String[] names) implements CustomPayload {
    public static final Id<TransferChestPacketPayload> ID = CustomPayload.id("tc:packet");
    public static final PacketCodec<PacketByteBuf, TransferChestPacketPayload> CODEC = PacketCodec.of(TransferChestPacketPayload::write, TransferChestPacketPayload::read);

    public static TransferChestPacketPayload read(PacketByteBuf buf) {
        int length = buf.readInt();
        String[] names = new String[length];
        for (int i = 0; i < length; i++) {
            names[i] = buf.readString();
        }
        return new TransferChestPacketPayload(names);
    }

    public void sendTo(PlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        write(buf);
        ServerPlayNetworking.send((ServerPlayerEntity) player, this);
        //ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PACKET_ID, buf);
    }

    public void onReceive(MinecraftClient ctx) {
        ctx.execute(() -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            ScreenHandler handler = player.currentScreenHandler;
            if (handler != null) {
                if (handler instanceof TransferChestGUIHandler) {
                    TransferChestGUIHandler tcHandler = (TransferChestGUIHandler) handler;
                    tcHandler.updatePanelEntries(names);
                }
            }
        });
    }

    public void write(PacketByteBuf buf) {
        buf.writeInt(names.length);
        for (String name : names) {
            buf.writeString(name);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
