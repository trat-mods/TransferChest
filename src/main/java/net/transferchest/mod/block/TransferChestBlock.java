package net.transferchest.mod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.transferchest.mod.abstraction.AHorizontalFacingInventoryBlock;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.loader.TCLoader;

import java.util.Random;

public class TransferChestBlock extends AHorizontalFacingInventoryBlock
{
    public static final Identifier ID = new Identifier(TCLoader.MOD_ID, "transfer_chest");
    public static final Identifier OPEN_SOUND = new Identifier(TCLoader.MOD_ID, "transfer_chest_open");
    public static final Identifier CLOSE_SOUND = new Identifier(TCLoader.MOD_ID, "transfer_chest_close");
    public static final BooleanProperty OPENED;
    
    static
    {
        OPENED = BooleanProperty.of("opened");
    }
    
    public TransferChestBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).materialColor(MaterialColor.BLACK).requiresTool().strength(40F, 900F).sounds(BlockSoundGroup.STONE));
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(OPENED, false)));
    }
    
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(OPENED);
        super.appendProperties(stateManager);
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ePos)
    {
        return Block.createCuboidShape(1F, 0F, 1F, 15F, 14F, 15F);
    }
    
    @Override public BlockEntity createBlockEntity(BlockView world)
    {
        return new TransferChestBlockEntity();
    }
    
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        int j = random.nextInt(2) * 2 - 1;
        int k = random.nextInt(2) * 2 - 1;
        double d = (double) pos.getX() + 0.5D + 0.25D * (double) j;
        double e = (double) ((float) pos.getY() + random.nextFloat());
        double f = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
        double g = 0.45D * (double) (random.nextFloat() * (float) j);
        double h = 0.45D * ((double) random.nextFloat()) * 0.1D;
        double l = 0.45D * (double) (random.nextFloat() * (float) k);
        world.addParticle(ParticleTypes.DRAGON_BREATH, d, e, f, g, h, l);
    }

    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        
        if(world.isClient) return ActionResult.SUCCESS;
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }
}
