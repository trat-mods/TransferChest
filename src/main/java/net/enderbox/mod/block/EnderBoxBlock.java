package net.enderbox.mod.block;

import net.enderbox.mod.abstraction.AHorizontalFacingInventoryBlock;
import net.enderbox.mod.entity.EnderBoxBlockEntity;
import net.enderbox.mod.loader.EBLoader;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EnderBoxBlock extends AHorizontalFacingInventoryBlock
{
    public static final Identifier ID = new Identifier(EBLoader.MOD_ID, "ender_box");
    
    public EnderBoxBlock()
    {
        super(FabricBlockSettings.of(Material.METAL).breakByHand((true)).strength(0.8F, 0.5F).sounds(BlockSoundGroup.METAL));
    }
    
    @Override public BlockEntity createBlockEntity(BlockView world)
    {
        return new EnderBoxBlockEntity();
    }
    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if(world.isClient) return ActionResult.SUCCESS;
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }
}
