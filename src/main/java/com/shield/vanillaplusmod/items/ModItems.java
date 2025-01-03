package com.shield.vanillaplusmod.items;

import com.shield.vanillaplusmod.ShieldVanillaPlusMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ShieldVanillaPlusMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
