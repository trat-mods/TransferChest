package net.transferchest.mod.gui.screen;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;

public class TransferChestScreen extends CottonInventoryScreen<TransferChestGUIHandler>
{
    public TransferChestScreen(TransferChestGUIHandler type, PlayerEntity player, Text title)
    {
        super(type, player, title);
    }
}
