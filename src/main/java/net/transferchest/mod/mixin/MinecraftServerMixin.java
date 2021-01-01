package net.transferchest.mod.mixin;

import net.transferchest.mod.api.ModLogger;
import net.transferchest.mod.api.Serializer;
import net.transferchest.mod.core.TransferChestHandler;
import net.transferchest.mod.core.SerializableInventory;
import net.transferchest.mod.loader.TCLoader;
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
        new ModLogger(TCLoader.MOD_ID).logInfo("SERIALIZING");
        TransferChestHandler.printStatus();
        SerializableInventory inventory = TransferChestHandler.getSerializableInventory();
        Serializer.serialize(inventory, ((MinecraftServer) (Object) this).getSaveProperties().getLevelName());
    }
    
    @Inject(at = @At("HEAD"), method = "prepareStartRegion")
    private void prepareStartRegion(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info)
    {
        TransferChestHandler.initialize();
        SerializableInventory obj = Serializer.deserialize(((MinecraftServer) (Object) this).getSaveProperties().getLevelName());
        if(obj != null)
        {
            TransferChestHandler.buildInventory(obj);
        }
        
        new ModLogger(TCLoader.MOD_ID).logInfo("DESERIALIZING");
        TransferChestHandler.printStatus();
    }
}
