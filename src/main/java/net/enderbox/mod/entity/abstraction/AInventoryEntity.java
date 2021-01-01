package net.enderbox.mod.entity.abstraction;

import net.enderbox.mod.api.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public abstract class AInventoryEntity extends BlockEntity implements ImplementedInventory
{
    protected DefaultedList<ItemStack> items;
    
    public AInventoryEntity(BlockEntityType<?> entity, int itemStackNumber)
    {
        super(entity);
        items = DefaultedList.ofSize(itemStackNumber, ItemStack.EMPTY);
    }
    
    @Override public DefaultedList<ItemStack> getItems()
    {
        return items;
    }
    
    @Override
    public boolean canPlayerUse(PlayerEntity player)
    {
        if(this.world.getBlockEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
    
    @Override
    public abstract boolean isValid(int slot, ItemStack stack);
    
    
    @Override
    public void fromTag(BlockState state, CompoundTag tag)
    {
        super.fromTag(state, tag);
    }
    
    @Override
    public CompoundTag toTag(CompoundTag tag)
    {
        return super.toTag(tag);
    }
}
