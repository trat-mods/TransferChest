package net.transferchest.mod.gui.handler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WPlayerInvPanel;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.initializer.TCGUIHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class TransferChestGUIHandler extends SyncedGuiDescription
{
    private TransferChestBlockEntity bufferEntity;
    
    public TransferChestGUIHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(TCGUIHandlers.TRANSFER_CHEST_GUI_HANDLER, syncId, playerInventory, getBlockInventory(context, TransferChestBlockEntity.INVENTORY_SIZE), null);
        
        initializeBufferEntity(context);
        open(playerInventory.player);
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        
        for(int i = 0; i < blockInventory.size(); i++)
        {
            WItemSlot current = WItemSlot.of(blockInventory, i);
            root.add(current, i * 18, 20);
        }
        
        WPlayerInvPanel playerInvPanel = this.createPlayerInventoryPanel();
        root.add(playerInvPanel, 0, 70);
        root.validate(this);
    }
    
    private void initializeBufferEntity(ScreenHandlerContext context)
    {
        TransferChestBlockEntity[] lambdaBypass = new TransferChestBlockEntity[1];
        
        context.run((world, blockPosition) ->
        {
            TransferChestBlockEntity temporaryEntity = (TransferChestBlockEntity) world.getBlockEntity(blockPosition);
            lambdaBypass[0] = temporaryEntity;
        });
        bufferEntity = lambdaBypass[0];
    }
    
    private void open(PlayerEntity player)
    {
        if(!world.isClient)
        {
            blockInventory.onOpen(playerInventory.player);
        }
    }
    
    @Override public void close(PlayerEntity player)
    {
        if(!world.isClient)
        {
            blockInventory.onClose(playerInventory.player);
        }
        super.close(player);
    }
}
