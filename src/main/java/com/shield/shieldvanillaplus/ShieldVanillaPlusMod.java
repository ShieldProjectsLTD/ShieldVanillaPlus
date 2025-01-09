package com.shield.shieldvanillaplus;

import com.shield.shieldvanillaplus.blocks.ModBlocks;
import com.shield.shieldvanillaplus.component.ModDataComponents;
import com.shield.shieldvanillaplus.enchantment.ModEnchantmentEffects;
import com.shield.shieldvanillaplus.items.ModCreativeModeTabs;
import com.shield.shieldvanillaplus.items.ModItems;
import com.shield.shieldvanillaplus.loot.ModLootModifiers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ShieldVanillaPlusMod.MODID)
public class ShieldVanillaPlusMod {
    public static final String MODID = "shieldvanillaplus";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ShieldVanillaPlusMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModDataComponents.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
