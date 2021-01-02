package net.transferchest.mod.entity;


import blue.endless.jankson.annotation.Nullable;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Direction;
import net.transferchest.mod.block.TransferChestBlock;
import net.transferchest.mod.core.TransferChestHandler;
import net.transferchest.mod.entity.abstraction.AInventoryEntity;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.initializer.TCEntities;
import net.transferchest.mod.initializer.TCItems;
import net.transferchest.mod.initializer.TCSounds;

public class TransferChestBlockEntity extends AInventoryEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory
{
    public static final int INVENTORY_SIZE = 10;
    private int viewers;
    
    public TransferChestBlockEntity()
    {
        super(TCEntities.TRANSFER_CHEST_BLOCK_ENTITY, INVENTORY_SIZE);
    }
    
    @Override public boolean isValid(int slot, ItemStack stack)
    {
        return stack.getItem() != TCItems.TRANSFER_CHEST_ITEM && !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock) && stack.getItem() != Items.ENDER_CHEST;
    }
    
    @Override public int[] getAvailableSlots(Direction side)
    {
        return new int[0];
    }
    
    @Override public boolean canInsert(int slot, ItemStack stack, Direction side)
    {
        return false;
    }
    
    @Override public boolean canExtract(int slot, ItemStack stack, Direction side)
    {
        return false;
    }
    
    @Override public Text getDisplayName()
    {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }
    
    
    @Override public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player)
    {
        items = TransferChestHandler.getTransferChestInventory();
        return new TransferChestGUIHandler(syncId, inv, ScreenHandlerContext.create(world, pos));
    }
    
    
    @Override public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf)
    {
        packetByteBuf.writeBlockPos(pos);
    }
    
    @Override public void onOpen(PlayerEntity player)
    {
        viewers++;
        if(viewers > 0 && !isOpened())
        {
            setOpened(true);
        }
    }
    
    @Override public void onClose(PlayerEntity player)
    {
        viewers--;
        if(viewers < 0) viewers = 0;
        
        if(viewers <= 0 && isOpened())
        {
            setOpened(false);
        }
    }
    
    private void setOpened(boolean opened)
    {
        if(!world.isClient)
        {
            this.world.setBlockState(this.pos, (BlockState) this.world.getBlockState(this.pos).with(TransferChestBlock.OPENED, opened), 0B1011);
            SoundEvent event = opened ? TCSounds.TRANSFER_CHEST_OPEN_SOUNDEVENT : TCSounds.TRANSFER_CHEST_CLOSE_SOUNDEVENT;
            this.world.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, event, SoundCategory.BLOCKS, 0.45F, this.world.random.nextFloat() * 0.2F + 1F);
        }
    }
    
    private boolean isOpened()
    {
        return this.world.getBlockState(pos).get(TransferChestBlock.OPENED);
    }
}
