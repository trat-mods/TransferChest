package net.transferchest.mod.initializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.transferchest.mod.block.TransferChestBlock;

public final class TCSounds {
    public static SoundEvent TRANSFER_CHEST_OPEN_SOUNDEVENT = SoundEvent.of(TransferChestBlock.OPEN_SOUND);
    public static SoundEvent TRANSFER_CHEST_CLOSE_SOUNDEVENT = SoundEvent.of(TransferChestBlock.CLOSE_SOUND);


    public static void initialize() {
        Registry.register(Registries.SOUND_EVENT, TransferChestBlock.OPEN_SOUND, TRANSFER_CHEST_OPEN_SOUNDEVENT);
        Registry.register(Registries.SOUND_EVENT, TransferChestBlock.CLOSE_SOUND, TRANSFER_CHEST_CLOSE_SOUNDEVENT);
    }
}
