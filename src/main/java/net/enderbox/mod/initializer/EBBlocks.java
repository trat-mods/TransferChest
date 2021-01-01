package net.enderbox.mod.initializer;

import net.enderbox.mod.block.EnderBoxBlock;
import net.minecraft.util.registry.Registry;


public final class EBBlocks
{
    public static final EnderBoxBlock ENDER_BOX_BLOCK = new EnderBoxBlock();
    
    public static void initialize()
    {
        
        Registry.register(Registry.BLOCK, EnderBoxBlock.ID, ENDER_BOX_BLOCK);
    }
}
