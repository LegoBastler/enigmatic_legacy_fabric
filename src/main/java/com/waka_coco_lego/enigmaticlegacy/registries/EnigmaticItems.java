package com.waka_coco_lego.enigmaticlegacy.registries;

import com.waka_coco_lego.enigmaticlegacy.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy.*;

public class EnigmaticItems {

    public static final Item IRONRING = registerItem("iron_ring", new IronRing(new Item.Settings().maxCount(1)));

    public static final ItemGroup ENIGMATICGROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.tryParse(MODID, "enigmatic_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.enigmaticCreativeTab"))
                    .icon(() -> new ItemStack(IRONRING)).entries((displayContext, entries) -> {
                        entries.add(IRONRING);
                    }).build());


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.tryParse(MODID, name), item);
    }

    public static void registerModItems() {
        LOGGER.info("Registering Mod Items for " + NAME);
    }

}
