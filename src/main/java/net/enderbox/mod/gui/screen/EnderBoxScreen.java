package net.enderbox.mod.gui.screen;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.enderbox.mod.gui.handler.EnderBoxGUIHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class EnderBoxScreen extends CottonInventoryScreen<EnderBoxGUIHandler>
{
    public EnderBoxScreen(EnderBoxGUIHandler type, PlayerEntity player, Text title)
    {
        super(type, player, title);
    }
}
