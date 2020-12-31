package net.enderbox.mod.mixin;

import net.enderbox.mod.core.EnderBoxHandler;
import net.enderbox.mod.core.SerializableInventory;
import net.enderbox.mod.utils.Serializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin
{
    
    public MinecraftServerMixin()
    {
    }
    
    @Inject(at = @At("HEAD"), method = "shutdown")
    private void shutdown(CallbackInfo info)
    {
        SerializableInventory inventory = EnderBoxHandler.getSerializableInventory();
        Serializer.serialize(inventory, ((MinecraftServer) (Object) this).getSaveProperties().getLevelName());
    }
    
    @Inject(at = @At("HEAD"), method = "prepareStartRegion")
    private void prepareStartRegion(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info)
    {
        SerializableInventory obj = Serializer.deserialize(((MinecraftServer) (Object) this).getSaveProperties().getLevelName());
        if(obj != null)
        {
            EnderBoxHandler.buildInventory(obj);
        }
    }
}
