package net.transferchest.mod.entity.abstraction;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.transferchest.mod.api.ImplementedSidedInventory;

public abstract class AInventoryEntity extends BlockEntity implements ImplementedSidedInventory {
    protected DefaultedList<ItemStack> items;

    public AInventoryEntity(BlockEntityType<?> entity, int itemStackNumber, BlockPos pos, BlockState state) {
        super(entity, pos, state);
        items = DefaultedList.ofSize(itemStackNumber, ItemStack.EMPTY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        else {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public abstract boolean isValid(int slot, ItemStack stack);


}
