package net.transferchest.mod.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.transferchest.mod.abstraction.AHorizontalFacingInventoryBlock;
import net.transferchest.mod.core.TransferChestHandler;
import net.transferchest.mod.entity.TransferChestBlockEntity;
import net.transferchest.mod.gui.handler.TransferChestGUIHandler;
import net.transferchest.mod.initializer.TCEntities;
import net.transferchest.mod.loader.TCLoader;
import org.jetbrains.annotations.Nullable;

public class TransferChestBlock extends AHorizontalFacingInventoryBlock {
    public static final Identifier ID = new Identifier(TCLoader.MOD_ID, "transfer_chest");
    public static final Identifier OPEN_SOUND = new Identifier(TCLoader.MOD_ID, "transfer_chest_open");
    public static final Identifier CLOSE_SOUND = new Identifier(TCLoader.MOD_ID, "transfer_chest_close");
    public static final BooleanProperty OPENED;

    static {
        OPENED = BooleanProperty.of("opened");
    }

    public TransferChestBlock() {
        super(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(40F, 900F).sounds(BlockSoundGroup.STONE));
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(OPENED, false)));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, TCEntities.TRANSFER_CHEST_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(OPENED);
        super.appendProperties(stateManager);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ePos) {
        return Block.createCuboidShape(1F, 0F, 1F, 15F, 14F, 15F);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
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


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TransferChestBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        if (!world.isClient) {
            TransferChestHandler.openGUI(world, (TransferChestGUIHandler) player.currentScreenHandler);
            return ActionResult.SUCCESS;
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }
}
