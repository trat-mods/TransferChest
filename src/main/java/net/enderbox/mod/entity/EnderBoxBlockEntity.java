package net.enderbox.mod.entity;

import com.sun.istack.internal.Nullable;
import net.enderbox.mod.core.EnderBoxHandler;
import net.enderbox.mod.entity.abstraction.AInventoryEntity;
import net.enderbox.mod.gui.handler.EnderBoxGUIHandler;
import net.enderbox.mod.initializer.EBEntities;
import net.enderbox.mod.initializer.EBItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class EnderBoxBlockEntity extends AInventoryEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory
{
    public static final int INVENTORY_SIZE = 9;
    
    public EnderBoxBlockEntity()
    {
        super(EBEntities.ENDER_BOX_BLOCK_ENTITY, INVENTORY_SIZE);
    }
    
    @Override public boolean isValid(int slot, ItemStack stack)
    {
        return stack.getItem() != EBItems.ENDER_BOX_ITEM && !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
    }
    
    @Override public Text getDisplayName()
    {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }
    
    @Override public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player)
    {
        items = EnderBoxHandler.getEnderBoxInventory();
        return new EnderBoxGUIHandler(syncId, inv, ScreenHandlerContext.create(world, pos));
    }
    
    
    @Override public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf)
    {
        packetByteBuf.writeBlockPos(pos);
    }
}
