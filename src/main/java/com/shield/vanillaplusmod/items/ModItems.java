package com.shield.vanillaplusmod.items;

import com.shield.vanillaplusmod.ShieldVanillaPlusMod;
import com.shield.vanillaplusmod.items.custom.Sandstone_Shovel;
import com.shield.vanillaplusmod.items.custom.Wither_Bone_Meal;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ShieldVanillaPlusMod.MODID);

    public static final DeferredItem<AxeItem> EXCALIBUR_AXE = ITEMS.register("excalibur_axe",
            () -> new AxeItem(ModToolTiers.EXCALIBUR, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.EXCALIBUR, 6.0F, -3.2F))));

    public static final DeferredItem<PickaxeItem> SANDSTONE_PICKAXE = ITEMS.register("sandstone_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SANDSTONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SANDSTONE, -2.0F, -3.0F))));

    public static final DeferredItem<Sandstone_Shovel> SANDSTONE_SHOVEL = ITEMS.register("sandstone_shovel",
            () -> new Sandstone_Shovel(ModToolTiers.SANDSTONE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SANDSTONE, -1.5F, -3.2F))));

    public static final DeferredItem<Item> WITHER_BONE = ITEMS.register("wither_bone",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Wither_Bone_Meal> WITHER_BONE_MEAL = ITEMS.register("wither_bone_meal",
            () -> new Wither_Bone_Meal(new Item.Properties()));

    public static final DeferredItem<Item> SLEEPING_HEART_TREE = ITEMS.register("sleeping_heart_tree",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AWAKENED_HEART_TREE = ITEMS.register("awakened_heart_tree",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
