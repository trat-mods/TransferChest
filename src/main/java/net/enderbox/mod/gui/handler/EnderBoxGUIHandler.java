package net.enderbox.mod.gui.handler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WPlayerInvPanel;
import net.enderbox.mod.entity.EnderBoxBlockEntity;
import net.enderbox.mod.initializer.EBGUIHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class EnderBoxGUIHandler extends SyncedGuiDescription
{
    private EnderBoxBlockEntity bufferEntity;
    
    public EnderBoxGUIHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(EBGUIHandlers.ENDER_BOX_GUI_HANDLER, syncId, playerInventory, getBlockInventory(context, EnderBoxBlockEntity.INVENTORY_SIZE), null);
        
        initializeBufferEntity(context);
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
        EnderBoxBlockEntity[] lambdaBypass = new EnderBoxBlockEntity[1];
        
        context.run((world, blockPosition) ->
        {
            EnderBoxBlockEntity temporaryEntity = (EnderBoxBlockEntity) world.getBlockEntity(blockPosition);
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
        
        }
        super.close(player);
    }
}
