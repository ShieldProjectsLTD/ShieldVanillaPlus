package com.shield.shieldvanillaplus.items;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
          DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ShieldVanillaPlusMod.MODID);

  public static final Supplier<CreativeModeTab> VANILLA_PLUS_TAB = CREATIVE_MODE_TAB.register("vanilla_plus_tab",
          ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.EXCALIBUR_AXE.get()))
                  .title(Component.translatable("creativetab.shieldvanillaplus.vanillaplus_tab"))
                  .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModItems.EXCALIBUR_AXE);
                    output.accept(ModItems.SANDSTONE_PICKAXE);
                    output.accept(ModItems.SANDSTONE_SHOVEL);
                    output.accept(ModItems.WITHER_BONE);
                    output.accept(ModItems.WITHER_BONE_MEAL);
                    output.accept(ModItems.SLEEPING_HEART_TREE);
                    output.accept(ModItems.AWAKENED_HEART_TREE);
                  }).build());

  public static void register(IEventBus bus) {
    CREATIVE_MODE_TAB.register(bus);
  }
}
