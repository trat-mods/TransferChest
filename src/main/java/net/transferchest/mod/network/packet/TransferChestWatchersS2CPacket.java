package net.transferchest.mod.network.packet;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.loader.TCLoader;

public class TransferChestWatchersS2CPacket extends CustomS2CPacket
{
    public static Identifier PACKET_ID = new Identifier(TCLoader.MOD_ID, "tc_packet");
    private String[] names;
    private int namesLength;
    
    public TransferChestWatchersS2CPacket(String[] names)
    {
        this.names = names;
        this.namesLength = names.length;
    }
    
    
    public void sendTo(PlayerEntity player)
    {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        write(buf);
        ServerPlayNetworking.send((ServerPlayerEntity) player, PACKET_ID, buf);
        //ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PACKET_ID, buf);
    }
    
    public void onReceive(MinecraftClient ctx)
    {
        ctx.execute(() ->
        {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            ScreenHandler handler = player.currentScreenHandler;
            if(handler != null)
            {
                if(handler instanceof TransferChestGUIHandler)
                {
                    TransferChestGUIHandler tcHandler = (TransferChestGUIHandler) handler;
                    tcHandler.updatePanelEntries(names);
                }
            }
        });
    }
    
    public void write(PacketByteBuf buf)
    {
        buf.writeInt(namesLength);
        for(String name : names)
        {
            buf.writeString(name);
        }
    }
    
    public static TransferChestWatchersS2CPacket read(PacketByteBuf buf)
    {
        int length = buf.readInt();
        String[] names = new String[length];
        for(int i = 0; i < length; i++)
        {
            names[i] = buf.readString();
        }
        return new TransferChestWatchersS2CPacket(names);
    }
}
